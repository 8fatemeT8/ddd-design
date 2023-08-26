package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.api.dto.*
import java.util.*

data class FunnelCreateDto(
    var name: String? = null,
    var productNumber: Int?=null,
    var steps: List<StepCreateDto>? = null
) : BaseCreateDto()

data class FunnelEditDto(
    var id: Int? = null,
    var name: String? = null,
    var productNumber: Int?=null,
    var steps: List<StepViewDto>? = null
) : BaseEditDto()

data class FunnelViewDto(
    var id: Int? = null,
    var name: String? = null,
    var project: ProjectViewDto? = null,
    var productNumber: Int?=null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null,
    var steps: List<StepViewDto>? = null
) : BaseViewDto()

data class FunnelListDto(
    var id: Int? = null,
    var name: String? = null,
) : BaseListDto()