package ir.smartech.cro.analytics.domain.client.api

import ir.smartech.cro.analytics.domain.client.api.entity.Client
import ir.smartech.cro.analytics.domain.client.spi.ClientRepository

/**
 * Service of [Client] class
 */
class ClientService(private val repo: ClientRepository) {

    fun upsert(entity: Client?): Client? = repo.save(entity)

    fun getById(id: Int): Client? = repo.findById(id)
}