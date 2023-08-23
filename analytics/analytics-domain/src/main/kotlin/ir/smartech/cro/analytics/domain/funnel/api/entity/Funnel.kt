package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.project.api.entity.Project

open class Funnel : BaseEntity() {
    var project: Project? = null

    var steps: List<Step?> = arrayListOf()

    var name: String? = null
}