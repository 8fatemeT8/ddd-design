package ir.smartech.cro.analytics.adapter.postgres.entity.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.JpaBaseCondition
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Audited
@Entity
@Table(name = "funnel_step_conditions")
class JpaStepCondition : JpaBaseCondition() {

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    var funnelStep: JpaStep? = null
}