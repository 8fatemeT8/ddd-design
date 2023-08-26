package ir.smartech.cro.analytics.rdb.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import jakarta.persistence.*

@MappedSuperclass
open class JpaBaseCondition : BaseEntity() {

    open var eventProperty: String? = null

    open var eventPropertyType: PropertyType? = null

    @Column
    @Enumerated(EnumType.STRING)
    open var operator: Operator? = null

    @Column
    open var value: String? = null

    var negate: Boolean? = null
}