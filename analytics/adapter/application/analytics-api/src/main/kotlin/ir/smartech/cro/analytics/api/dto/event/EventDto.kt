package ir.smartech.cro.analytics.api.dto.event

import ir.smartech.cro.analytics.api.dto.*
import java.util.*

data class EventListDto(
    var id: Int? = null,
    var name: String? = null,
    var pathEligible: Boolean? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseListDto()

data class EventViewDto(
    var id: Int? = null,
    var name: String? = null,
    var pathEligible: Boolean? = null,
    var project: IdDto? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseViewDto()

data class EventCreateDto(
    var name: String? = null, var pathEligible: Boolean? = null
) : BaseCreateDto()

data class EventEditDto(var id: Int? = null, var name: String? = null, var pathEligible: Boolean? = null) :
    BaseEditDto()