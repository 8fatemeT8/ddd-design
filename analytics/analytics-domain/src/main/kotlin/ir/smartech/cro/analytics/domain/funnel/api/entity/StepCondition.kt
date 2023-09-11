package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseCondition

/**
 * this class get the fields from parent and saves the funnelStep conditions
 */
class StepCondition : BaseCondition() {
    var funnelStep: Step? = null
}