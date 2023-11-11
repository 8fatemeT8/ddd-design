package ir.smartech.cro.analytics.api.dto.funnel

import java.util.Date

data class FunnelQueryRequest(
    var completionTime: Long? = null,
    var startDate: Date? = null,
    var endDate: Date? = null,
    var splitBy: String? = null,
    var stepNumbers: Long? = null
)