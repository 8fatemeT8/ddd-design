package ir.smartech.cro.storage.data.postgres.entity

import io.hypersistence.utils.hibernate.type.json.JsonType
import ir.smartech.cro.storage.data.postgres.ReturnType
import jakarta.persistence.*
import org.hibernate.annotations.Type

/**
 * each client has one of this entity
 * and in this entity save a hashmap ('data') that refer to client's business data format.
 * we must save the field name and field type ( as a string to string map) for
 * validating client's data in 'receive api' (CollectorController).
 */
@Entity
@Table(name = "client_schemas")
class ClientSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @OneToOne
    var client: Client? = null

    @Column(columnDefinition = "json")
    @Type(JsonType::class)
    var data: HashMap<String, ReturnType>? = hashMapOf()
}