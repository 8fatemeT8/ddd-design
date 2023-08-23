package ir.smartech.cro.analytics.domain.project.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity

class Project : BaseEntity() {
    var name: String? = null

    var description: String? = null

    var enabled: Boolean? = null
}