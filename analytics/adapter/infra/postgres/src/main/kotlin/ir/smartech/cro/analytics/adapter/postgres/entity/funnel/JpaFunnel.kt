package ir.smartech.cro.analytics.adapter.postgres.entity.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.BaseEntity
import ir.smartech.cro.analytics.adapter.postgres.entity.JpaProject
import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType
import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*

@Entity
@Table(name = "funnels")
class JpaFunnel : BaseEntity() {
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    var predecessor: JpaFunnel? = null

    var enable: Boolean? = true

    var isDeleted: Boolean? = false

    @ManyToOne
    @JoinColumn(name = "project_id")
    var project: JpaProject? = null

    @OneToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "funnel_id")
    @Fetch(FetchMode.SELECT)
    var jpaSteps: List<JpaStep?> = arrayListOf()

    @Column
    var startDate: Date? = null

    @Column
    var expDate: Date? = null

    @Enumerated(EnumType.STRING)
    var eventType: EventType? = null

    var name: String? = null
}