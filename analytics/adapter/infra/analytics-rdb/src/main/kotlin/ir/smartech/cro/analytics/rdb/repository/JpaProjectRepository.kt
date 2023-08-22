package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.JpaProject
import org.springframework.data.jpa.repository.JpaRepository

interface JpaProjectRepository : JpaRepository<JpaProject, Int> {
}