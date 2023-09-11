package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.domain.client.api.entity.Client
import ir.smartech.cro.analytics.domain.client.spi.ClientRepository
import ir.smartech.cro.analytics.rdb.mapper.ClientMapper
import ir.smartech.cro.analytics.rdb.repository.JpaClientRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * this class implement the [ClientRepository] methods
 * and provide the appropriate mapper for method input and output
 */
@Component
@Transactional
class ClientRepositoryAdapter(private val repo: JpaClientRepository, val clientMapper: ClientMapper) :
    ClientRepository {
    override fun save(entity: Client?): Client? {
        if (entity == null) return null
        val jpaClient = clientMapper.toDestination(entity)
        val result = repo.save(jpaClient)
        return clientMapper.toSource(result)
    }

    override fun findById(id: Int): Client? {
        val client = repo.findById(id)
            .orElseThrow { ResponseException(ErrorCodes.NOT_FOUND, "the client with $id id not found") }
        return clientMapper.toSource(client)
    }
}