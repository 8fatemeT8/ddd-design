package ir.smartech.cro.analytics.api.dto.event

import ir.smartech.cro.analytics.api.dto.IdDto
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

data class PropertyCreateDto(
    var name: String? = null,
    var propertyType: PropertyType? = null,
    var event: IdDto? = null
)

data class PropertyViewDto(
    var id: Int? = null,
    var name: String? = null,
    var propertyType: PropertyType? = null,
    var event: EventViewDto? = null
)