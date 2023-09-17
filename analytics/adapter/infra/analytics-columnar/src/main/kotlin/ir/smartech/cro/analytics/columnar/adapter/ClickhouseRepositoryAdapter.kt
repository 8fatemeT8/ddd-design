package ir.smartech.cro.analytics.columnar.adapter

import cc.blynk.clickhouse.ClickHouseDataSource
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.SegmentFunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.*
import ir.smartech.cro.analytics.domain.funnel.spi.ClickhouseRepository
import org.springframework.stereotype.Component

@Component
class ClickhouseRepositoryAdapter(private val dataSource: ClickHouseDataSource) : ClickhouseRepository {

    override fun getFunnelQueryById(funnel: Funnel, completionTime: Long): List<FunnelQueryDto> {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toQueryString(completionTime)
        val resultSet = statement.executeQuery(queryString)
        val result = arrayListOf<FunnelQueryDto>()
        while (resultSet.next()) {
            result.add(FunnelQueryDto().apply {
                level = resultSet.getLong(1)
                this.result = resultSet.getLong(2)
            })
        }
        resultSet.close()
        statement.close()
        return result
    }

    override fun getFunnelSplitBy(funnel: Funnel, completionTime: Long, splitBy: String): List<FunnelQueryDto> {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toQueryStringWithSplit(completionTime, splitBy)
        val resultSet = statement.executeQuery(queryString)
        val result = arrayListOf<FunnelQueryDto>()
        while (resultSet.next()) {
            result.add(FunnelQueryDto().apply {
                level = resultSet.getLong(1)
                this.result = resultSet.getLong(2)
                splitValue = resultSet.getString(3)
            })
        }
        resultSet.close()
        statement.close()
        return result
    }


    override fun getFunnelSegment(
        funnel: Funnel, completionTime: Long, steps: List<Step>
    ): List<SegmentFunnelQueryDto> {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toSegmentQuery(completionTime, steps)
        val resultSet = statement.executeQuery(queryString)
        val result = arrayListOf<SegmentFunnelQueryDto>()
        while (resultSet.next()) {
            result.add(SegmentFunnelQueryDto().apply {
                userIds = listOf(resultSet.getArray(1).array).map { it as Long }
            })
        }
        resultSet.close()
        statement.close()
        return result
    }
}
