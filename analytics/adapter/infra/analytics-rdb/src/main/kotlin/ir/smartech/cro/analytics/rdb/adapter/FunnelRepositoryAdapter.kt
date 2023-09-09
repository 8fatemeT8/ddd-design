package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.rdb.mapper.funnel.FunnelMapper
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * this class implement the [FunnelRepository] methods like crud
 * and provide the appropriate mapper for method input and output
 */
@Component
@Transactional
class FunnelRepositoryAdapter(private val repo: JpaFunnelRepository, private val funnelMapper: FunnelMapper) :
    FunnelRepository {
    override fun save(entity: Funnel?): Funnel? {
        if (entity == null) return null
        // map to jpa entity
        val jpaFunnel = funnelMapper.toDestination(entity)
        val saved = repo.save(jpaFunnel)
        // map to domain entity
        return funnelMapper.toSource(saved)
    }

    override fun findById(id: Int): Funnel? {
        val dbObject = repo.findById(id)
            .orElseThrow { ResponseException(ErrorCodes.NOT_FOUND, "the funnel with $id id not found") }
        return funnelMapper.toSource(dbObject)
    }

    override fun findByIdAndClientId(id: Int, clientId: Int): Funnel? {
        val dbObject = repo.findByIdAndClientId(id, clientId) ?: throw ResponseException(
            ErrorCodes.NOT_FOUND,
            "the funnel with $id id not found"
        )
        return funnelMapper.toSource(dbObject)
    }

    @Transactional
    override fun findAll(): List<Funnel?> {
        val data = repo.findAll()
        return data.map { funnelMapper.toSource(it) }
    }

    override fun findAllByClientId(clientId: Int): Iterable<Funnel?> {
        val data = repo.findAllByClientId(clientId)
        return data.map { funnelMapper.toSource(it) }
    }

    override fun deleteById(id: Int, clientId: Int) {
        if (!repo.existsByIdAndClientId(id, clientId))
            throw ResponseException(ErrorCodes.FORBIDDEN, "you dont have access to modify this object")
        repo.deleteById(id)
    }

    override fun delete(entity: Funnel?, clientId: Int) {
        if (entity == null) return
        val jpaFunnel = funnelMapper.toDestination(entity)
        if (!repo.existsByIdAndClientId(entity.id!!, clientId))
            throw ResponseException(ErrorCodes.FORBIDDEN, "you dont have access to modify this object")
        repo.delete(jpaFunnel)
    }

    override fun findAllByNameList(name: String, pageable: Any, clientId: Int): Any {
        return repo.findAllByNameLikeAndClientId("%$name%", clientId, pageable as Pageable)
            .map { funnelMapper.toSource(it) }
    }
}