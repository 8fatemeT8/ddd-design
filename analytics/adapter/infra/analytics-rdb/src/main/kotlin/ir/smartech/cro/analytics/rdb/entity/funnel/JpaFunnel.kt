package ir.smartech.cro.analytics.rdb.entity.funnel

import ir.smartech.cro.analytics.rdb.entity.BaseEntity
import ir.smartech.cro.analytics.rdb.entity.JpaClient
import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.envers.AuditMappedBy
import org.hibernate.envers.Audited

/**
 * this is the entity of Funnel in analytics-domain module
 * in this class we can use the jpa features
 */
@Audited
@Entity
@Table(name = "funnels")
class JpaFunnel : BaseEntity() {
    var isDeleted: Boolean? = false

    @ManyToOne
    @JoinColumn(name = "client_id")
    var client: JpaClient? = null

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "funnel_id")
    @Fetch(FetchMode.SUBSELECT)
    @AuditMappedBy(mappedBy = "funnel")
    var steps: List<JpaStep?> = arrayListOf()

    var name: String? = null

    var productNumber: Int?=null
}