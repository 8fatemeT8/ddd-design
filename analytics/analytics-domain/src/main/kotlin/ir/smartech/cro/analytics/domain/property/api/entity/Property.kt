package ir.smartech.cro.analytics.domain.property.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.domain.project.api.entity.Project

class Property : BaseEntity() {
    var project: Project? = null

    var name: String? = null

    var propertyType: PropertyType? = null

    var event: Event? = null

//    var parent: Property? = null
}