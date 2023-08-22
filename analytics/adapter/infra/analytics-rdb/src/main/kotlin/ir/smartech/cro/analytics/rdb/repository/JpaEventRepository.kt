package ir.smartech.cro.analytics.rdb.repository

import ir.smartech.cro.analytics.rdb.entity.event.JpaEvent
import org.springframework.data.jpa.repository.JpaRepository

interface JpaEventRepository : JpaRepository<JpaEvent, Int> {
    fun findAllByProjectId(projectId: Int): List<JpaEvent>
    fun findByIdAndProjectId(id: Int, projectId: Int): JpaEvent
}