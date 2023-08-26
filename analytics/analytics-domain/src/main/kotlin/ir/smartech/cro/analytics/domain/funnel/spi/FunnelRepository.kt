package ir.smartech.cro.analytics.domain.funnel.spi

import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel

interface FunnelRepository {
    fun save(entity: Funnel?): Funnel?
    fun findById(id: Int): Funnel?
    fun findByIdAndProjectId(id: Int, projectId: Int): Funnel?
    fun findAll(): Iterable<Funnel?>
    fun findAllByProjectId(projectId: Int): Iterable<Funnel?>
    fun deleteById(id: Int, projectId: Int)
    fun delete(entity: Funnel?, projectId: Int)
    fun findAllByNameList(name: String, pageable: Any): Any
}