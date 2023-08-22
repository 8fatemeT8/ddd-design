package ir.smartech.cro.analytics.rdb.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.rdb.entity.event.JpaProperty
import jakarta.persistence.*

@MappedSuperclass
open class JpaBaseCondition : BaseEntity() {

    @ManyToOne
    open var property: JpaProperty? = null

    @Column
    @Enumerated(EnumType.STRING)
    open var operator: Operator? = null

    @Column
    open var value: String? = null
}