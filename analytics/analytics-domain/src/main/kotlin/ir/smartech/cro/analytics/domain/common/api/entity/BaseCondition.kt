package ir.smartech.cro.analytics.domain.common.api.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

open class BaseCondition : BaseEntity() {
    var eventProperty: String? = null

    var eventPropertyType: PropertyType? = null

    var operator: Operator? = null

    var value: String? = null

    var negate: Boolean? = null
}