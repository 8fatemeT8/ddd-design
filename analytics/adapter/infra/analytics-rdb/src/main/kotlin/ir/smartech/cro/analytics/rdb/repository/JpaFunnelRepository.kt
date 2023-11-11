package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaFunnel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaFunnelRepository : JpaRepository<JpaFunnel, Int> {
    fun findAllByClientId(clientId: Int): List<JpaFunnel>
    fun findByIdAndClientId(id: Int, clientId: Int): JpaFunnel?
    fun findAllByNameLikeAndClientId(name: String, clientId: Int, pageable: Pageable): Page<JpaFunnel>
    fun existsByIdAndClientId(id: Int, clientId: Int): Boolean
}