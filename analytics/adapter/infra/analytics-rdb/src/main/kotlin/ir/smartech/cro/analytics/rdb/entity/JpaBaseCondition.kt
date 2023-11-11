package ir.smartech.cro.analytics.rdb.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import jakarta.persistence.*

/**
 * this class must be the parent of all condition classes
 */
@MappedSuperclass
open class JpaBaseCondition : BaseEntity() {

    open var eventProperty: String? = null

    @Enumerated(EnumType.STRING)
    open var eventPropertyType: PropertyType? = null

    @Column(name = "condition_operator")
    @Enumerated(EnumType.STRING)
    open var operator: Operator? = null

    @Column(name = "condition_value")
    open var value: String? = null

    var negate: Boolean? = null
}