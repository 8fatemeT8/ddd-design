package ir.smartech.cro.analytics.domain.common.api.entity

import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.domain.funnel.api.enums.EventType

class Property {
    var project: Project? = null

    var name: String? = null

    var propertyType: PropertyType? = null

//    var event: Event? = null

    var eventType: EventType? = null

    var parent: Property? = null

}