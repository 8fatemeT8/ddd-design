package ir.smartech.cro.analytics.adapter.postgres.entity

import jakarta.persistence.*

@Entity
@Table
class JpaProject : BaseEntity() {
    @Column(nullable = false)
    var name: String? = null

    @Column(length = 1_000)
    var description: String? = null

    @Column(nullable = false)
    var enabled: Boolean? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var jpaProperties: ArrayList<JpaProperty?> = arrayListOf()

}