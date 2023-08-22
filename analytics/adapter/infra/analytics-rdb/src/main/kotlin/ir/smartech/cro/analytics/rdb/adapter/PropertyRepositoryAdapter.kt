package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.property.api.entity.Property
import ir.smartech.cro.analytics.domain.property.spi.PropertyRepository
import ir.smartech.cro.analytics.rdb.mapper.event.PropertyMapper
import ir.smartech.cro.analytics.rdb.repository.JpaPropertyRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class PropertyRepositoryAdapter(private val repo: JpaPropertyRepository, private val mapper: PropertyMapper) :
    PropertyRepository {
    override fun save(entity: Property): Property {
        val jpaProperty = mapper.toDestination(entity) ?: throw NotFoundException()
        val saved = repo.save(jpaProperty)
        return mapper.toSource(saved)!!
    }

    override fun saveAll(entities: List<Property>): List<Property> {
        val jpaProperties = entities.map { mapper.toDestination(it) }
        val saveProperties = repo.saveAll(jpaProperties)
        return saveProperties.map { mapper.toSource(it)!! }
    }

    override fun findAllByEventIdAndProjectId(eventId: Int, projectId: Int): List<Property> {
        val result = repo.findAllByEventIdAndProjectId(eventId, projectId)
        return result.map { mapper.toSource(it)!! }
    }
}