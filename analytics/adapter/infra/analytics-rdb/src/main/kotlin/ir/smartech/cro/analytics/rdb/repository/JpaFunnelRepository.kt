package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaFunnel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaFunnelRepository: JpaRepository<JpaFunnel, Int> {
    // TODO add methods here
}