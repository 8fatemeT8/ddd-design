package ir.smartech.cro.analytics.adapter.postgres.adapter

import ir.smartech.cro.analytics.adapter.postgres.mapper.funnel.FunnelMapper
import ir.smartech.cro.analytics.adapter.postgres.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class FunnelRepositoryAdapter(private val repo: JpaFunnelRepository, private val funnelMapper: FunnelMapper) :
    FunnelRepository {
    override fun save(entity: Funnel): Funnel? {
        // map to jpa entity
        val jpaFunnel = funnelMapper.toSource(entity) ?: throw NotFoundException()
        val saved = repo.save(jpaFunnel)
        // map to domain entity
        return funnelMapper.toDestination(saved)
    }

    override fun findById(id: Int): Optional<Funnel> {
        val dbObject = repo.findById(id).get() ?: throw NotFoundException()
        return Optional.of(funnelMapper.toDestination(dbObject)!!)
    }

    override fun findAll(): Iterable<Funnel?> {
        val data = repo.findAll()
        return data.map { funnelMapper.toDestination(it) }
    }

    override fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    override fun delete(entity: Funnel) {
        val jpaFunnel = funnelMapper.toSource(entity) ?: throw NotFoundException()
        repo.delete(jpaFunnel)
    }
}