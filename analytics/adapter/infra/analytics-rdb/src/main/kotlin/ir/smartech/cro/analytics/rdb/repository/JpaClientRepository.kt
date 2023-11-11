package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.JpaClient
import org.springframework.data.jpa.repository.JpaRepository

interface JpaClientRepository : JpaRepository<JpaClient, Int> {
}