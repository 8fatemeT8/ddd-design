package ir.smartech.cro.analytics.api.dto.funnel

data class StepCreateDto(
    var eventName: String? = null,
    var seq: Int? = null,
    var stepConditions: List<ConditionCreateDto?>? = null
)

data class StepViewDto(
    var id: Int? = null,
    var eventName: String? = null,
    var seq: Int? = null,
    var stepConditions: List<ConditionViewDto?>? = null
)