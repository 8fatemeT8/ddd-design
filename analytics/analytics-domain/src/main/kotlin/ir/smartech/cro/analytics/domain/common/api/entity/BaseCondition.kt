package ir.smartech.cro.analytics.domain.common.api.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator

open class BaseCondition : BaseEntity() {
    var eventProperty: String? = null

    var operator: Operator? = null

    var value: String? = null
}