package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepQueryDto
import ir.smartech.cro.analytics.domain.client.api.entity.Client
import ir.smartech.cro.analytics.domain.funnel.api.FunnelQueryBuilder

/**
 * this class stores funnel definition information such as :
 * 1 . name
 * 2 . client (this field shows the funnel owner)
 * 3 . productNumber (this field refers to client's business id)
 * 4 . steps (this field stores funnel eventName sequence and their conditions)
 */
open class Funnel : BaseEntity() {
    var client: Client? = null

    var productNumber: String? = null

    var steps: List<Step?> = arrayListOf()

    var name: String? = null

    var isDeleted: Boolean? = false
}

/**
 * this method uses the [FunnelQueryBuilder] for creating FunnelQuery (sql query) from [Funnel] params
 * its normal funnel
 */
fun Funnel.toQueryString(completionTime: Long, startTimestamp: Long?, endTimestamp: Long?) = run {
    val funnelSteps = getStepQueryDto()
    FunnelQueryBuilder
        .steps(funnelSteps)
        .productNumber(productNumber!!)
        .completionTime(completionTime)
        .setTimeFrame(startTimestamp, endTimestamp)
        .build()
}

/**
 * this method does something like Funnel.toQueryString() and the different is in splitBy input (set groupBy on data)
 */
fun Funnel.toQueryStringWithSplit(completionTime: Long, splitBy: String, startTimestamp: Long?, endTimestamp: Long?) = run {
    val funnelSteps = getStepQueryDto()
    FunnelQueryBuilder
        .steps(funnelSteps)
        .productNumber(productNumber!!)
        .splitBy(splitBy)
        .completionTime(completionTime)
        .build()
}

/**
 * this method create segmentQuery, it's the dropped users between firstStep and secondStep
 */
fun Funnel.toSegmentQuery(completionTime: Long, steps: List<Step>, startTimestamp: Long?, endTimestamp: Long?) = run {
    val funnelSteps = getStepQueryDto(steps)
    FunnelQueryBuilder
        .steps(funnelSteps)
        .productNumber(productNumber!!)
        .completionTime(completionTime)
        .build()
}

private fun Funnel.getStepQueryDto(input: List<Step>? = null) =
    input?.sortedBy { it.stepNumber }?.map {
        StepQueryDto().apply {
            stepNumber = it.stepNumber
            eventName = it.eventName
            conditions = it.stepConditions?.map { it2 ->
                it2.operator?.parser(it2.value)?.apply {
                    eventProperty = it2.eventProperty
                    operator = it2.operator
                    eventPropertyType = it2.eventPropertyType
                    negate = it2.negate
                }
            }
        }
    } ?: steps.sortedBy { it?.stepNumber }.map {
        StepQueryDto().apply {
            stepNumber = it?.stepNumber
            eventName = it?.eventName
            conditions = it?.stepConditions?.map { it2 ->
                it2.operator?.parser(it2.value)?.apply {
                    eventProperty = it2.eventProperty
                    operator = it2.operator
                    eventPropertyType = it2.eventPropertyType
                    negate = it2.negate
                }
            }
        }
    }
