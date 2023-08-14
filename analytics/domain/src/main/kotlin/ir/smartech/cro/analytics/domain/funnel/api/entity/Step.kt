package ir.smartech.cro.analytics.domain.funnel.api.entity

class Step {
    var id: Int? = null

    var stepConditions: List<StepCondition> = arrayListOf()

    var funnel: Funnel? = null

    var seq: Long? = null

    var eventName: String? = null

}