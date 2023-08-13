package ir.smartech.cro.analytics.domain.funnel.api.entity

class Step {
    var conditions: List<StepCondition> = arrayListOf()

    var funnel: Funnel? = null

    var seq: Long? = null

    var eventName: String? = null

}