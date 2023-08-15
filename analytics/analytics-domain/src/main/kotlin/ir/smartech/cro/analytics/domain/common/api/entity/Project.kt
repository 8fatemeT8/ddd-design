package ir.smartech.cro.analytics.domain.common.api.entity

class Project {
    var id: Int? = null

    var name: String? = null

    var description: String? = null

    var enabled: Boolean? = null

    var properties: List<Property?> = mutableListOf()

//    var events: List<Event>? = mutableListOf()
}