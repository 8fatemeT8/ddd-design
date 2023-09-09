package ir.smartech.cro.analytics.rdb.entity

import jakarta.persistence.*
import org.hibernate.envers.Audited

/**
 * this is the entity of Client in analytics-domain module
 * in this class we can use the jpa features
 */
@Audited
@Entity
@Table(name = "clients")
class JpaClient : BaseEntity() {
    @Column(nullable = false)
    var name: String? = null

    @Column(length = 1_000)
    var description: String? = null

    @Column(nullable = false)
    var enabled: Boolean? = null
}