package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity

class Step : BaseEntity() {
    var stepConditions: List<StepCondition> = arrayListOf()

    var funnel: Funnel? = null

    var seq: Long? = null

    var eventName: String? = null
}