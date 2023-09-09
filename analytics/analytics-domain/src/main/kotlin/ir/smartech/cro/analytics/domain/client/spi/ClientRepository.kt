package ir.smartech.cro.analytics.domain.client.spi

import ir.smartech.cro.analytics.domain.client.api.entity.Client

interface ClientRepository {
    fun save(entity: Client?): Client?

    fun findById(id: Int): Client?
}