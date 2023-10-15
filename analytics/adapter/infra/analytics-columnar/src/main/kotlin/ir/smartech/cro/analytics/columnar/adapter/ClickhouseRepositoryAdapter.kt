package ir.smartech.cro.analytics.columnar.adapter

import com.clickhouse.jdbc.ClickHouseDataSource
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.SegmentFunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.*
import ir.smartech.cro.analytics.domain.funnel.spi.ClickhouseRepository
import org.springframework.stereotype.Component

@Component
class ClickhouseRepositoryAdapter(private val dataSource: ClickHouseDataSource) : ClickhouseRepository {

    override fun getFunnelQueryById(
        funnel: Funnel, completionTime: Long, startTimestamp: Long?, endTimestamp: Long?
    ): List<FunnelQueryDto> {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toQueryString(completionTime, startTimestamp, endTimestamp)
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

    override fun getFunnelSplitBy(
        funnel: Funnel, completionTime: Long, splitBy: String, startTimestamp: Long?, endTimestamp: Long?
    ): List<FunnelQueryDto> {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toQueryStringWithSplit(completionTime, splitBy, startTimestamp, endTimestamp)
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
        funnel: Funnel, completionTime: Long, dropped: Int, startTimestamp: Long?, endTimestamp: Long?
    ): SegmentFunnelQueryDto {
        val statement = dataSource.connection.createStatement()
        val queryString = funnel.toSegmentQuery(completionTime, dropped, startTimestamp, endTimestamp)
        val resultSet = statement.executeQuery(queryString)
        val result = SegmentFunnelQueryDto().apply {
            while (resultSet.next()) {
                userIds.add(resultSet.getString(1))
            }
        }
        resultSet.close()
        statement.close()
        return result
    }
}
