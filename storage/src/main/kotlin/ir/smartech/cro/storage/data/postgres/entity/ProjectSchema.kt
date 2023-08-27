package ir.smartech.cro.storage.data.postgres.entity

import io.hypersistence.utils.hibernate.type.json.JsonType
import ir.smartech.cro.storage.data.postgres.ReturnType
import jakarta.persistence.*
import org.hibernate.annotations.Type

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