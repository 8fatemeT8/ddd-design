package ir.smartech.cro.storage.data.postgres.entity

import jakarta.persistence.*

/**
 * this entity save user (client) data
 */
@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String? = null

     var username: String? = null

    var password: String? = null
    var topicName: String? = null
        get() = "${name}_topic"

    @OneToOne
    var projectSchema: ProjectSchema? = null
}