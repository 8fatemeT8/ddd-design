package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.rdb.mapper.funnel.FunnelMapper
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class FunnelRepositoryAdapter(private val repo: JpaFunnelRepository, private val funnelMapper: FunnelMapper) :
    FunnelRepository {
    override fun save(entity: Funnel): Funnel? {
        // map to jpa entity
        val jpaFunnel = funnelMapper.toDestination(entity) ?: throw NotFoundException()
        val saved = repo.save(jpaFunnel)
        // map to domain entity
        return funnelMapper.toSource(saved)
    }

    @Transactional
    override fun findById(id: Int): Optional<Funnel> {
        val dbObject = repo.findById(id).get()
        return Optional.of(funnelMapper.toSource(dbObject)!!)
    }

    override fun findByIdAndProjectId(id: Int, projectId: Int): Optional<Funnel> {
        val dbObject = repo.findByIdAndProjectId(id, projectId)
        return Optional.of(funnelMapper.toSource(dbObject)!!)
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

    override fun delete(entity: Funnel) {
        val jpaFunnel = funnelMapper.toDestination(entity) ?: throw NotFoundException()
        repo.delete(jpaFunnel)
    }
}