package ir.smartech.cro.analytics.adapter.postgres.entity.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "funnel_steps")
class JpaStep : BaseEntity() {

    @OneToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "funnel_step_id")
    var jpaStepConditions: List<JpaStepCondition> = arrayListOf()

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    var funnel: JpaFunnel? = null

    @Column
    var seq: Long? = null

    @Column(nullable = false)
    var eventName: String? = null

}