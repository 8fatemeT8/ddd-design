package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.api.dto.BaseCreateDto
import ir.smartech.cro.analytics.api.dto.BaseEditDto
import ir.smartech.cro.analytics.api.dto.BaseListDto
import ir.smartech.cro.analytics.api.dto.BaseViewDto
import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType
import java.util.*

data class FunnelCreateDto(
    var name: String? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var steps: List<StepCreateDto>? = null
) : BaseCreateDto()

data class FunnelEditDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var steps: List<StepViewDto>? = null
) : BaseEditDto()

data class FunnelViewDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null,
    var steps: List<StepViewDto>? = null
) : BaseViewDto()

data class FunnelListDto(
    var name: String? = null,
    var id: Int? = null,
    var startDate: Date? = null,
    var expDate: Date? = null,
    var enable: Boolean? = null,
    var eventType: EventType? = null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null
) : BaseListDto()