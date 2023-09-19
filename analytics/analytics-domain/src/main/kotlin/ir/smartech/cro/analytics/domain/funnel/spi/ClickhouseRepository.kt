package ir.smartech.cro.analytics.domain.funnel.spi

import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.SegmentFunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step

interface ClickhouseRepository {
    fun getFunnelQueryById(funnel: Funnel, completionTime: Long, startTimestamp: Long?, endTimestamp: Long?): List<FunnelQueryDto>
    fun getFunnelSplitBy(funnel: Funnel, completionTime: Long, splitBy: String, startTimestamp: Long?, endTimestamp: Long?): List<FunnelQueryDto>
    fun getFunnelSegment(funnel: Funnel, completionTime: Long, steps: List<Step>, startTimestamp: Long?, endTimestamp: Long?): List<SegmentFunnelQueryDto>
}