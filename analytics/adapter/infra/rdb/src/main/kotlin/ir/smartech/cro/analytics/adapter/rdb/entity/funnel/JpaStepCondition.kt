package ir.smartech.cro.analytics.adapter.rdb.entity.funnel

import ir.smartech.cro.analytics.adapter.rdb.entity.JpaBaseCondition
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Audited
@Entity
@Table(name = "funnel_step_conditions")
class JpaStepCondition : JpaBaseCondition() {

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    var funnelStep: JpaStep? = null
}