package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.common.api.BaseService
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository

/**
 * Service provide the [Funnel] basic logics such as crud
 */
class FunnelService(private val funnelRepository: FunnelRepository) : BaseService<Funnel> {
    override fun upsert(entity: Funnel?): Funnel? {
        return funnelRepository.save(entity)
    }

    override fun findById(id: Int): Funnel? {
        return funnelRepository.findById(id)
    }

    override fun findByIdAndClientId(id: Int, clientId: Int): Funnel? {
        return funnelRepository.findByIdAndClientId(id, clientId)
    }

    override fun findAll(): List<Funnel?> {
        return funnelRepository.findAll().toList()
    }

    override fun findAllClientId(clientId: Int): List<Funnel?> {
        return funnelRepository.findAllByClientId(clientId).toList()
    }

    override fun deleteById(id: Int, clientId: Int): Boolean {
        funnelRepository.deleteById(id, clientId)
        return true
    }

    override fun delete(entity: Funnel?, clientId: Int): Boolean {
        funnelRepository.delete(entity, clientId)
        return true
    }

    fun findAllByContainsName(name: String, pageable: Any, clientId: Int): Any {
        return funnelRepository.findAllByNameList(name, pageable, clientId)
    }
}