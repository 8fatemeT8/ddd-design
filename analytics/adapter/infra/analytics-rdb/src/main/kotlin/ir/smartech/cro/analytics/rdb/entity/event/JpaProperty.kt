package ir.smartech.cro.analytics.rdb.entity.event

import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.rdb.entity.BaseEntity
import ir.smartech.cro.analytics.rdb.entity.JpaProject
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Audited
@Table
@Entity(name = "properties")
class JpaProperty : BaseEntity() {
    @ManyToOne(optional = true)
    var project: JpaProject? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var propertyType: PropertyType? = null

    @ManyToOne
    var event: JpaEvent? = null

//    @ManyToOne(optional = true, fetch = FetchType.EAGER)
//    var parent: JpaProperty? = null

}