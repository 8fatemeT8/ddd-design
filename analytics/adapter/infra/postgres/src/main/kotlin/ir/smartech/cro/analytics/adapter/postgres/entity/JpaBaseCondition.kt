package ir.smartech.cro.analytics.adapter.postgres.entity

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
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