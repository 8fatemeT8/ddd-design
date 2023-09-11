package ir.smartech.cro.analytics.domain.funnel.api.dto


/**
 * this class return the required fields in funnel query
 */
class StepQueryDto {
    var conditions: List<StepConditionQueryBaseDto?>? = arrayListOf()

    var stepNumber: Long? = null

    var eventName: String? = null
}