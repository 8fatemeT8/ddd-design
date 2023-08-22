package ir.smartech.cro.analytics.domain.project.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.property.api.entity.Property
import ir.smartech.cro.analytics.domain.event.api.entity.Event

class Project : BaseEntity() {
    var name: String? = null

    var description: String? = null

    var enabled: Boolean? = null

    var properties: List<Property?> = mutableListOf()

    var events: List<Event>? = mutableListOf()
}