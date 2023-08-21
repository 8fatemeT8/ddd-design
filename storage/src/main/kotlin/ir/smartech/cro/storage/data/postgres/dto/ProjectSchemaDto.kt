package ir.smartech.cro.storage.data.postgres.dto

import ir.smartech.cro.storage.data.postgres.ReturnType

data class ProjectSchemaDto(
    var id: Int? = null,
    var data: HashMap<String, ReturnType>? = null
)