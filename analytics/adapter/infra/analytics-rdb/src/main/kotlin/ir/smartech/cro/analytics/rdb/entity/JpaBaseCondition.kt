package ir.smartech.cro.analytics.rdb.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import jakarta.persistence.*

@MappedSuperclass
open class JpaBaseCondition : BaseEntity() {

    open var eventProperty: String? = null

    @Column
    @Enumerated(EnumType.STRING)
    open var operator: Operator? = null

    @Column
    open var value: String? = null
}