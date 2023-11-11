package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.api.dto.*
import java.util.*

/**
 * CreateDto of Funnel domain
 */
data class FunnelCreateDto(
    var name: String? = null,
    var productNumber: Int?=null,
    var steps: List<StepCreateDto>? = null
) : BaseCreateDto()

/**
 * EditDto of Funnel domain
 */
data class FunnelEditDto(
    var id: Int? = null,
    var name: String? = null,
    var productNumber: Int?=null,
    var steps: List<StepViewDto>? = null
) : BaseEditDto()

/**
 * ViewDto of Funnel domain
 */
data class FunnelViewDto(
    var id: Int? = null,
    var name: String? = null,
    var client: ClientViewDto? = null,
    var productNumber: Int?=null,
    var createdDate: Date? = null,
    var modifiedDate: Date? = null,
    var steps: List<StepViewDto>? = null
) : BaseViewDto()

/**
 * ListDto of Funnel domain
 */
data class FunnelListDto(
    var id: Int? = null,
    var name: String? = null,
) : BaseListDto()