package ir.smartech.cro.analytics.adapter.userapi.dto.funnel

import ir.smartech.cro.analytics.adapter.userapi.dto.IdDto
import ir.smartech.cro.analytics.domain.common.api.enums.Operator

data class ConditionCreateDto(var operator: Operator? = null, var value: String? = null, var property: IdDto? = null)
data class ConditionViewDto(
    var id: Int? = null,
    var operator: Operator? = null,
    var value: String? = null,
    var property: IdDto? = null
)