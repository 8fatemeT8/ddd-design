package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.funnel.api.dto.StepQueryDto

class FunnelQueryBuilder private constructor() {

    companion object {
        private var steps: List<StepQueryDto?>? = null
        private var productNumber: String? = null
        private var completionTime: Long? = null
        private var splitBy: String? = null
//        private var segmentFirstSteStep: String? = null
//        private var segmentSecondSteStep: String? = null

        fun steps(dto: List<StepQueryDto>): Companion {
            if (steps == null) steps = dto
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

//        fun segment(firstStep: String, secondSte: String): Companion {
//            segmentFirstSteStep = firstStep
//            segmentSecondSteStep = secondSte
//            return this
//        }

        fun completionTime(duration: Long): Companion {
            completionTime = duration
            return this
        }

        fun build(): String {
            val result = """
                SELECT level, count() as result ${splitBy?.let { ", $it" } ?: ""}
                FROM (
                         SELECT user_id, ${splitBy?.let { "$it ," } ?: ""}
                                windowFunnel($completionTime, 'strict_order')(timestamp, ${getSteps()}) AS level
                         from analytics.intrack_events
                         where product_id = '$productNumber'
                         group by user_id ${splitBy?.let { ", $it" } ?: ""}
                         )
                GROUP BY level ${splitBy?.let { ", $it" } ?: ""}
                ORDER BY level ASC;
            """.trimIndent()

            resetFields()

            return result
        }

        private fun getSteps() = steps?.sortedBy { it?.stepNumber }?.map { it?.getStepQuery() }?.joinToString(",")

        fun resetFields() {
            steps = null
            productNumber = null
            completionTime = null
            splitBy = null
        }
    }
}