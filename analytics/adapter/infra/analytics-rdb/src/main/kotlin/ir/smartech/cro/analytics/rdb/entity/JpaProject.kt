package ir.smartech.cro.analytics.rdb.entity

import ir.smartech.cro.analytics.rdb.entity.event.JpaEvent
import ir.smartech.cro.analytics.rdb.entity.event.JpaProperty
import jakarta.persistence.*
import org.hibernate.envers.AuditMappedBy
import org.hibernate.envers.Audited

@Audited
@Entity
@Table(name = "projects")
class JpaProject : BaseEntity() {
    @Column(nullable = false)
    var name: String? = null

    @Column(length = 1_000)
    var description: String? = null

    @Column(nullable = false)
    var enabled: Boolean? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @AuditMappedBy(mappedBy = "project")
    var jpaProperties: List<JpaProperty?> = arrayListOf()

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @AuditMappedBy(mappedBy = "project")
    var events: List<JpaEvent>? = arrayListOf()

}