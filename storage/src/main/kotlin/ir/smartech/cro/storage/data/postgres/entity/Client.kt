package ir.smartech.cro.storage.data.postgres.entity

import jakarta.persistence.*

/**
 * this entity save Client authentication data such as name, username, password, etc.
 */
@Entity
@Table(name = "clients")
class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String? = null

     var username: String? = null

    var password: String? = null
    var topicName: String? = null
        get() = "${name}_topic"

    @OneToOne
    var clientSchema: ClientSchema? = null
}