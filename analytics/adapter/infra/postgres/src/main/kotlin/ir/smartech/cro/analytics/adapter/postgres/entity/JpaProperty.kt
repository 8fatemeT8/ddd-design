package ir.smartech.cro.analytics.adapter.postgres.entity

import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType
import jakarta.persistence.*

@Table
@Entity
class JpaProperty : BaseEntity() {
    @ManyToOne(optional = true)
    var project: JpaProject? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var propertyType: PropertyType? = null

    @Enumerated(EnumType.STRING)
    var eventType: EventType? = null

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    var parent: JpaProperty? = null

}