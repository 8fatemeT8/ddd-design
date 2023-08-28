package ir.smartech.cro.storage.data.postgres.dto

import ir.smartech.cro.storage.data.postgres.ReturnType

/**
 * this dto is used for create and update ProjectSchema
 */
data class ProjectSchemaDto(
    var id: Int? = null,
    var data: HashMap<String, ReturnType>? = null
)