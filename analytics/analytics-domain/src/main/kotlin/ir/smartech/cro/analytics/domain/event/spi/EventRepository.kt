package ir.smartech.cro.analytics.domain.event.spi

import ir.smartech.cro.analytics.domain.event.api.entity.Event
import java.util.*

interface EventRepository {
    fun save(entity: Event): Event?
    fun findById(id: Int): Optional<Event>
    fun findByIdAndProjectId(id: Int, projectId: Int): Optional<Event>
    fun findAll(): Iterable<Event?>
    fun findAllProjectId(projectId: Int): Iterable<Event?>
    fun deleteById(id: Int)
    fun delete(entity: Event)
}