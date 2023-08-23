package ir.smartech.cro.analytics.rdb.entity

import jakarta.persistence.*
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
}