package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.domain.event.spi.EventRepository
import ir.smartech.cro.analytics.rdb.mapper.event.EventMapper
import ir.smartech.cro.analytics.rdb.repository.JpaEventRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventRepositoryAdapter(private val repo: JpaEventRepository, private val eventMapper: EventMapper) :
    EventRepository {
    override fun save(entity: Event): Event? {
        val jpaEvent = eventMapper.toDestination(entity) ?: throw NotFoundException()
        val result = repo.save(jpaEvent)
        return eventMapper.toSource(result)
    }

    override fun findById(id: Int): Optional<Event> {
        val result = repo.findById(id).get()
        return Optional.of(eventMapper.toSource(result)!!)
    }

    override fun findByIdAndProjectId(id: Int, projectId: Int): Optional<Event> {
        val result = repo.findByIdAndProjectId(id, projectId)
        return Optional.of(eventMapper.toSource(result)!!)
    }

    override fun findAll(): Iterable<Event?> {
        val result = repo.findAll()
        return result.map { eventMapper.toSource(it) }
    }

    override fun findAllProjectId(projectId: Int): Iterable<Event?> {
        val result = repo.findAllByProjectId(projectId)
        return result.map { eventMapper.toSource(it) }
    }

    override fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    override fun delete(entity: Event) {
        val jpaEntity = eventMapper.toDestination(entity) ?: throw NotFoundException()
        repo.delete(jpaEntity)
    }
}