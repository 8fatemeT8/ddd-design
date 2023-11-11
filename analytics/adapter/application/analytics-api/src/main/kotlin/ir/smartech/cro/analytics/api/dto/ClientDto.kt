package ir.smartech.cro.analytics.api.dto

import java.util.*

/**
 * CreateDto of Client domain
 */
data class ClientCreateDto(var name: String? = null, var description: String? = null, var enabled: Boolean? = null)

/**
 * ViewDto of Client domain
 */
data class ClientViewDto(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
)
