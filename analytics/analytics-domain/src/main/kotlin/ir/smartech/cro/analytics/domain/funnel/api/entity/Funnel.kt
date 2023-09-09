package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepQueryDto
import ir.smartech.cro.analytics.domain.client.api.entity.Client

/**
 * this class stores funnel definition information such as :
 * 1 . name
 * 2 . client (this field shows the funnel owner)
 * 3 . productNumber (this field refers to client's business id)
 * 4 . steps (this field stores funnel eventName sequence and their conditions)
 */
open class Funnel : BaseEntity() {
    var client: Client? = null

    var productNumber: Int? = null

    var steps: List<Step?> = arrayListOf()

    var name: String? = null

    var isDeleted: Boolean? = false
}

/**
 * this method uses in mapping [Funnel] to [FunnelQueryDto] and prepare required query fields
 */
fun Funnel.toQueryDto() = run {
    val that = this
    FunnelQueryDto().apply {
        clientId = client?.id
        this.productNumber = that.productNumber
        steps = that.steps.sortedBy { it?.stepNumber }.map {
            StepQueryDto().apply {
                stepNumber = it?.stepNumber
                eventName = it?.eventName
                conditions = it?.stepConditions?.map { it2 ->
                    it2.operator?.parser(it2.value).apply {
                        this?.eventProperty = it2.eventProperty
                        this?.operator = it2.operator
                        this?.eventPropertyType = it2.eventPropertyType
                        this?.negate = it2.negate
                    }
                }
            }
        }
    }
}
