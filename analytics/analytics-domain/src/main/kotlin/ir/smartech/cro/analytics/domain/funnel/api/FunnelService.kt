package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.common.api.BaseService
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository

class FunnelService(private val funnelRepository: FunnelRepository) : BaseService<Funnel> {
    override fun upsert(entity: Funnel): Funnel? {
        return funnelRepository.save(entity)
    }

    override fun findById(id: Int): Funnel {
        return funnelRepository.findById(id).get()
    }

    override fun findByIdAndProjectId(id: Int, projectId: Int): Funnel {
        return funnelRepository.findByIdAndProjectId(id, projectId).get()
    }

    override fun findAll(): List<Funnel?> {
        return funnelRepository.findAll().toList()
    }

    override fun findAllProjectId(projectId: Int): List<Funnel?> {
        return funnelRepository.findAllByProjectId(projectId).toList()
    }

    override fun deleteById(id: Int): Boolean {
        funnelRepository.deleteById(id)
        return true
    }

    override fun delete(entity: Funnel): Boolean {
        funnelRepository.delete(entity)
        return true
    }
}