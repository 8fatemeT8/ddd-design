package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseCondition

class StepCondition : BaseCondition() {
    var funnelStep: Step? = null
}