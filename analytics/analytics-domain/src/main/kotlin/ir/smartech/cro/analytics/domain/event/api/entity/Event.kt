package ir.smartech.cro.analytics.domain.event.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import ir.smartech.cro.analytics.domain.property.api.entity.Property
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step

class Event : BaseEntity() {

    var name: String? = null

    var pathEligible: Boolean? = null

    var properties: List<Property>? = arrayListOf()

    var project: Project? = null

    var steps: List<Step>? = arrayListOf()
}