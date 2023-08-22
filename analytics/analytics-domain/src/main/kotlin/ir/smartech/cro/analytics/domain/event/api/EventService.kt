package ir.smartech.cro.analytics.domain.event.api

import ir.smartech.cro.analytics.domain.common.api.BaseService
import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.domain.event.spi.EventRepository

class EventService(private val eventRepository: EventRepository) : BaseService<Event> {
    override fun upsert(entity: Event): Event? = eventRepository.save(entity)

    override fun findById(id: Int): Event = eventRepository.findById(id).get()

    override fun findByIdAndProjectId(id: Int, projectId: Int): Event =
        eventRepository.findByIdAndProjectId(id, projectId).get()

    override fun findAll(): List<Event?> = eventRepository.findAll().toList()

    override fun findAllProjectId(projectId: Int): List<Event?> = eventRepository.findAllProjectId(projectId).toList()

    override fun deleteById(id: Int): Boolean {
        eventRepository.deleteById(id)
        return true
    }

    override fun delete(entity: Event): Boolean {
        eventRepository.delete(entity)
        return true
    }
}