package ir.smartech.cro.storage.data.postgres.dto

import ir.smartech.cro.storage.data.postgres.ReturnType

/**
 * Dto class for [ClientSchema], @see [ClientSchema]
 */
data class ClientSchemaDto(
    var id: Int? = null,
    var data: HashMap<String, ReturnType>? = null
)