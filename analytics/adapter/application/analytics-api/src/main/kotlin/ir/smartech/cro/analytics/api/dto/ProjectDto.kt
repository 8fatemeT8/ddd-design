package ir.smartech.cro.analytics.api.dto

import java.util.*


data class ProjectCreateDto(var name: String? = null, var description: String? = null, var enabled: Boolean? = null)

data class ProjectViewDto(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
)
