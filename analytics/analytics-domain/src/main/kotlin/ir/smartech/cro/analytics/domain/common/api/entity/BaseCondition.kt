package ir.smartech.cro.analytics.domain.common.api.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.property.api.entity.Property

open class BaseCondition : BaseEntity() {
    var property: Property? = null

    var operator: Operator? = null

    var value: String? = null
}