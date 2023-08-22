package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.api.dto.event.EventViewDto
import ir.smartech.cro.analytics.api.dto.IdDto

data class StepCreateDto(
    var event: IdDto? = null,
    var seq: Int? = null,
    var stepConditions: List<ConditionCreateDto?>? = null
)

data class StepViewDto(
    var id: Int? = null,
    var event: EventViewDto? = null,
    var seq: Int? = null,
    var stepConditions: List<ConditionViewDto?>? = null
)