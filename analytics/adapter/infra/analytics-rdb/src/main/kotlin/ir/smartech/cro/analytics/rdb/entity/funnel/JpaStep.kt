package ir.smartech.cro.analytics.rdb.entity.funnel

import ir.smartech.cro.analytics.rdb.entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.envers.AuditMappedBy
import org.hibernate.envers.Audited

@Audited
@Entity
@Table(name = "funnel_steps")
class JpaStep : BaseEntity() {

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "funnel_step_id")
    @AuditMappedBy(mappedBy = "funnelStep")
    var stepConditions: List<JpaStepCondition> = arrayListOf()

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    var funnel: JpaFunnel? = null

    @Column
    var seq: Long? = null

    @Column(nullable = false)
    var eventName: String? = null

}