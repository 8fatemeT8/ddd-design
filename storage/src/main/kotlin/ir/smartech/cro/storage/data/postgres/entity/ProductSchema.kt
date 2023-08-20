package ir.smartech.cro.storage.data.postgres.entity

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType
import ir.smartech.cro.storage.data.postgres.ReturnType
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "product_schema")
class ProductSchema {

    @EmbeddedId
    var productSchemaId: ProductSchemaId? = null

    @Column(columnDefinition = "jsonb")
    @Type(JsonBinaryType::class)
    var data: HashMap<String, ReturnType>? = hashMapOf()
}