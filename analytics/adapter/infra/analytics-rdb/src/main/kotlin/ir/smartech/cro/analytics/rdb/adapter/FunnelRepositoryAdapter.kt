package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.rdb.mapper.funnel.FunnelMapper
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

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

    override fun findByIdAndProjectId(id: Int, projectId: Int): Funnel? {
        val dbObject = repo.findByIdAndProjectId(id, projectId) ?: throw ResponseException(
            ErrorCodes.NOT_FOUND,
            "the funnel with $id id not found"
        )
        return funnelMapper.toSource(dbObject)
    }

    @Transactional
    override fun findAll(): Iterable<Funnel?> {
        val data = repo.findAll()
        return data.map { funnelMapper.toSource(it) }
    }

    override fun findAllByProjectId(projectId: Int): Iterable<Funnel?> {
        val data = repo.findAllByProjectId(projectId)
        return data.map { funnelMapper.toSource(it) }
    }

    override fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    override fun delete(entity: Funnel?) {
        if (entity == null) return
        val jpaFunnel = funnelMapper.toDestination(entity)
        repo.delete(jpaFunnel)
    }
}