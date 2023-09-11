package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity

/**
 * this entity saves eventName and its conditions.
 * in funnel definition, the order of steps is very important, so we decided to add stepNumber.
 * stepNumber is an ascending number.
 */
class Step : BaseEntity() {
    var stepConditions: List<StepCondition>? = arrayListOf()

    var funnel: Funnel? = null

    var stepNumber: Long? = null

    var eventName: String? = null
}