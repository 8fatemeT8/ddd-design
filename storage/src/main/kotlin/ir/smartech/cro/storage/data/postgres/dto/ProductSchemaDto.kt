package ir.smartech.cro.storage.data.postgres.dto

data class ProductSchemaDto(
    var id: Int? = null,
    var businessId: Int? = null,
    var data: HashMap<String, String>? = null
)