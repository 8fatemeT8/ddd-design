package ir.smartech.cro.storage.data.postgres.entity

import io.hypersistence.utils.hibernate.type.json.JsonType
import ir.smartech.cro.storage.data.postgres.ReturnType
import jakarta.persistence.*
import org.hibernate.annotations.Type

/**
 * each user (client) has one of this entity
 * and in this entity save a hashmap ('data') that refer to client business data format
 * we must save the field name and field type ( as a string to string map) for
 * validating client data in 'receive api' (CollectorController)
 */
@Entity
@Table(name = "project_schemas")
class ProjectSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @OneToOne
    var user: User? = null

    @Column(columnDefinition = "json")
    @Type(JsonType::class)
    var data: HashMap<String, ReturnType>? = hashMapOf()
}