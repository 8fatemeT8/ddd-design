package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepQueryDto
import ir.smartech.cro.analytics.domain.project.api.entity.Project

open class Funnel : BaseEntity() {
    var project: Project? = null

    var productNumber: Int? = null

    var steps: List<Step?> = arrayListOf()

    var name: String? = null

    var isDeleted: Boolean? = false
}

fun Funnel.toQueryDto() = run {
    val that = this
    FunnelQueryDto().apply {
        projectId = project?.id
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
