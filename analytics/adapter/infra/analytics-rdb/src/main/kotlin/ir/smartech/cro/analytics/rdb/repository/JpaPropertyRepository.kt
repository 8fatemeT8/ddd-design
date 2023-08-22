package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.event.JpaProperty
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyRepository : JpaRepository<JpaProperty, Int> {
    fun findAllByEventIdAndProjectId(id: Int, projectId: Int): List<JpaProperty>
}