package ir.smartech.cro.analytics.rdb.entity.event

import ir.smartech.cro.analytics.rdb.entity.BaseEntity
import ir.smartech.cro.analytics.rdb.entity.JpaProject
import ir.smartech.cro.analytics.rdb.entity.funnel.JpaStep
import jakarta.persistence.*
import org.hibernate.envers.AuditMappedBy
import org.hibernate.envers.Audited

@Audited
@Entity
@Table(name = "events")
class JpaEvent : BaseEntity() {

    var name: String? = null

    var pathEligible: Boolean? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @AuditMappedBy(mappedBy = "event")
    var properties: List<JpaProperty>? = arrayListOf()

    @ManyToOne
    var project: JpaProject? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @AuditMappedBy(mappedBy = "event")
    var steps: List<JpaStep>? = arrayListOf()
}