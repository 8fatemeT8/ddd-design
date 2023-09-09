package ir.smartech.cro.analytics.domain.funnel.api.dto

/**
 * this class return the required fields in funnel query
 */
class FunnelQueryDto {
    var clientId: Int? = null

    var productNumber: Int? = null

    var steps: List<StepQueryDto?> = arrayListOf()
}