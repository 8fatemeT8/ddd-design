package ir.smartech.cro.analytics.domain.client.api.entity

import ir.smartech.cro.analytics.domain.common.api.entity.BaseEntity

/**
 * this class show the client basic data like name and description
 */
class Client : BaseEntity() {
    var name: String? = null

    var description: String? = null

    var enabled: Boolean? = null
}