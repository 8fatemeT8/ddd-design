package ir.smartech.cro.analytics.api.dto.funnel

/**
 * CreateDto of Step domain
 */
data class StepCreateDto(
    var eventName: String? = null,
    var stepNumber: Int? = null,
    var stepConditions: List<ConditionCreateDto?>? = null
)

/**
 * ViewDto of Step domain
 */
data class StepViewDto(
    var id: Int? = null,
    var eventName: String? = null,
    var stepNumber: Int? = null,
    var stepConditions: List<ConditionViewDto?>? = null
)