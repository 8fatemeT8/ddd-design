package ir.smartech.cro.storage.data.postgres.dto

import ir.smartech.cro.storage.data.postgres.ReturnType

data class ProductSchemaDto(
    var id: Int? = null,
    var businessId: Int? = null,
    var data: HashMap<String, ReturnType>? = null
)