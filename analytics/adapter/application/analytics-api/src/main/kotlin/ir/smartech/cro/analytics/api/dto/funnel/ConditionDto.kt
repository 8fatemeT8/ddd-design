package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.domain.common.api.enums.Operator

data class ConditionCreateDto(
    var operator: Operator? = null,
    var value: String? = null,
    var eventProperty: String? = null
)

data class ConditionViewDto(
    var id: Int? = null,
    var operator: Operator? = null,
    var value: String? = null,
    var eventProperty: String? = null,
)