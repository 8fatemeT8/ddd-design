package ir.smartech.cro.analytics.domain.funnel.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.Project
import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType
import java.util.*

open class Funnel {
    var id: Int? = null
        private set

    var predecessor: Funnel? = null
        private set

    var project: Project? = null

    var steps: List<Step?> = arrayListOf()

    var startDate: Date? = null

    var expDate: Date? = null

    var eventType: EventType? = null

    var name: String? = null

    var enable: Boolean? = true

    var isDeleted: Boolean? = false

    fun computedPredecessor(parent: Funnel) {
        predecessor = parent
    }
//
}