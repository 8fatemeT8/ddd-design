package ir.smartech.cro.analytics.domain.funnel.api.dto


class StepQueryDto {
    var conditions: List<StepConditionQueryBaseDto?>? = arrayListOf()

    var stepNumber: Long? = null

    var eventName: String? = null
}