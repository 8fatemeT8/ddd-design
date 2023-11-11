package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.funnel.api.dto.StepQueryDto

class FunnelQueryBuilderV2 private constructor() {
    companion object {
        private var steps: List<StepQueryDto?>? = null
        private var productNumber: String? = null
        private var completionTime: Long? = null
        private var splitBy: String? = null
        private var startTimestamp: Long? = null
        private var endTimestamp: Long? = null
        private var segment: Boolean = false
        private var dropped: Int? = null

        fun setTimeFrame(startTimestamp: Long?, endTimestamp: Long?): Companion {
            this.startTimestamp = startTimestamp
            this.endTimestamp = endTimestamp
            return this
        }

        fun dropped(droppedStep: Int): Companion {
            dropped = droppedStep
            return this
        }

        fun steps(dto: List<StepQueryDto>, segment: Boolean = false): Companion {
            if (steps == null) steps = dto
            this.segment = segment
            return this
        }

        fun productNumber(input: String): Companion {
            productNumber = input
            return this
        }

        fun splitBy(input: String): Companion {
            splitBy = input
            return this
        }

        fun completionTime(duration: Long): Companion {
            completionTime = duration
            return this
        }

        fun build(): String {
            val result = """
                select user_id, string96 , max(level) from
                ( WITH
                    Step1 AS (
                        SELECT
                            user_id, timestamp AS time , string96
                        FROM
                            analytics.intrack_events
                        WHERE
                                event_name = 'search_pageview' and product_id = '12' and  has( ['iOS','Android'],string96 )
                    ),
                     Res1 AS (
                        SELECT
                            user_id, Step1.time as time_start, Step1.time as time_end, '1' as level , string96
                        FROM
                            Step1
                    ),
                    Step2 AS (
                        SELECT
                            user_id, timestamp AS time , string96
                        FROM
                            analytics.intrack_events
                        WHERE
                                event_name = 'product_detail_pageview' and product_id = '12' and  has( ['iOS'],string96 )
                    ),
                    Res2 AS (
                        SELECT
                            user_id, Res1.time_start as time_start, Step2.time as time_end, '2' as level , string96
                        FROM
                            Res1 JOIN Step2 on Res1.user_id = Step2.user_id
                        where Res1.time_end <= Step2.time and (Step2.time - Res1.time_start) <= 2592000
                    )
                SELECT Distinct user_id, ,level ${splitBy?.let { ", $it" } ?: ""}"} from Res1
                UNION ALL
                SELECT Distinct user_id, level ${splitBy?.let { ", $it" } ?: ""}"} from Res2)
                  UNION ALL
                SELECT Distinct user_id, level ${splitBy?.let { ", $it" } ?: ""}"} from Res3)
                
                
                group by user_id, ${splitBy?.let { ", $it" } ?: ""}"}
                
                
                
                
                
                
                
                
                
                
                
                SELECT ${if (segment) "user_id" else " level, count() as result ${splitBy?.let { ", $it" } ?: ""}"}
                FROM (
                         SELECT user_id, ${splitBy?.let { "$it ," } ?: ""}
                                windowFunnel($completionTime, 'strict_order')(timestamp, ${getSteps()}) AS level
                         from analytics.intrack_events
                         where product_id = '$productNumber'
                         ${getTimeFrame()}

                         group by user_id ${splitBy?.let { ", $it" } ?: ""}
                         )
                ${if (segment) "where level = $dropped" else "GROUP BY level ${splitBy?.let { ", $it" } ?: ""}"}
                ORDER BY level ASC;
            """.trimIndent()

            resetFields()

            return result
        }

        private fun getTimeFrame() = """${if (startTimestamp != null) "AND $startTimestamp <= timestamp" else ""}
             ${if (endTimestamp != null) "AND $endTimestamp >= timestamp" else ""}""".trimMargin()

        private fun getSteps() = steps?.sortedBy { it?.stepNumber }?.map { it?.getStepQuery() }?.joinToString(",")

        fun resetFields() {
            steps = null
            productNumber = null
            completionTime = null
            splitBy = null
            startTimestamp = null
            endTimestamp = null
            segment = false
        }
    }
}