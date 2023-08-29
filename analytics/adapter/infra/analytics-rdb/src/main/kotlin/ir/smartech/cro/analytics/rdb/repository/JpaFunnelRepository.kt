package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaFunnel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaFunnelRepository : JpaRepository<JpaFunnel, Int> {
    fun findAllByProjectId(projectId: Int): List<JpaFunnel>
    fun findByIdAndProjectId(id: Int, projectId: Int): JpaFunnel?
    fun findAllByNameLikeAndProjectId(name: String, projectId: Int, pageable: Pageable): Page<JpaFunnel>
    fun existsByIdAndProjectId(id: Int, projectId: Int): Boolean
}