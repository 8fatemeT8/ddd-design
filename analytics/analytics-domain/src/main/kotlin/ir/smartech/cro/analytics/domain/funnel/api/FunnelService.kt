package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository

class FunnelService(private val funnelRepository: FunnelRepository) {
    fun upsert(entity: Funnel): Funnel? {
        return funnelRepository.save(entity)
    }

    fun findById(id: Int): Funnel {
        return funnelRepository.findById(id).get()
    }

    fun findAll(): List<Funnel?> {
        return funnelRepository.findAll().toList()
    }

    fun deleteById(id: Int): Boolean {
        funnelRepository.deleteById(id)
        return true
    }

    fun delete(entity: Funnel): Boolean {
        funnelRepository.delete(entity)
        return true
    }
}