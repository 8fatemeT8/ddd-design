package ir.smartech.cro.analytics.domain.funnel.api.dto

data class FunnelQueryDto(var level: Long? = null, var result: Long? = null, var splitValue: String? = null)
data class FunnelQueryResponse(var stepNumber: Long? = null, var count: Long? = 0, var splitValue: String? = null)
data class SegmentFunnelQueryDto(var userIds: List<Long>? = null)