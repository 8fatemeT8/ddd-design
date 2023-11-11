package ir.smartech.cro.analytics.domain.funnel.spi

import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel

interface FunnelRepository {
    fun save(entity: Funnel?): Funnel?
    fun findById(id: Int): Funnel?
    fun findByIdAndClientId(id: Int, clientId: Int): Funnel?
    fun findAll(): Iterable<Funnel?>
    fun findAllByClientId(clientId: Int): Iterable<Funnel?>
    fun deleteById(id: Int, clientId: Int)
    fun delete(entity: Funnel?, clientId: Int)
    fun findAllByNameList(name: String, pageable: Any, clientId: Int): Any
}