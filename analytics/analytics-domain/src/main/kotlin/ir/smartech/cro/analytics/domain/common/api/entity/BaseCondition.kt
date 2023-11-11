package ir.smartech.cro.analytics.domain.common.api.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

/**
 * these fields saving funnelStep conditions and the usage of these is when the client wants to get funnel query
 */
open class BaseCondition : BaseEntity() {
    var eventProperty: String? = null

    var eventPropertyType: PropertyType? = null

    var operator: Operator? = null

    var value: String? = null

    var negate: Boolean? = null
}