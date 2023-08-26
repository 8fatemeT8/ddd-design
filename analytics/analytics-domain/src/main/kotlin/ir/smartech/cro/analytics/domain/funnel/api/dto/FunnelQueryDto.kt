package ir.smartech.cro.analytics.domain.funnel.api.dto

class FunnelQueryDto {
    var projectId: Int? = null

    var productNumber: Int? = null

    var steps: List<StepQueryDto?> = arrayListOf()
}