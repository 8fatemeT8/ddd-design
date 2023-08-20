package ir.smartech.cro.storage.data.postgres.entity

import jakarta.persistence.Embeddable
import jakarta.persistence.ManyToOne
import java.io.Serializable

@Embeddable
class ProductSchemaId : Serializable {

    @ManyToOne
    var product: Product? = null
    var businessId: Int? = null
}