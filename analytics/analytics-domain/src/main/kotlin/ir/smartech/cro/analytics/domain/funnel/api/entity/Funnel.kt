package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import java.util.*

open class Funnel : BaseEntity() {
    var predecessor: Funnel? = null

    var project: Project? = null

    var steps: List<Step?> = arrayListOf()

    var startDate: Date? = null

    var expDate: Date? = null

    var name: String? = null

    var enable: Boolean? = true

    var isDeleted: Boolean? = false
    fun computedPredecessor(parent: Funnel) {
        predecessor = parent
    }
//
}