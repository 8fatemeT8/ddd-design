package ir.smartech.cro.analytics.domain.property.api

import ir.smartech.cro.analytics.domain.property.api.entity.Property
import ir.smartech.cro.analytics.domain.property.spi.PropertyRepository

class PropertyService(private val propertyRepository: PropertyRepository) {

    fun upsert(entity: Property): Property {
        return propertyRepository.save(entity)
    }

    fun saveAll(entities: List<Property>): List<Property> {
        return propertyRepository.saveAll(entities)
    }

    fun getPropertiesByEvent(eventId: Int, projectId: Int): List<Property> {
        return propertyRepository.findAllByEventIdAndProjectId(eventId,projectId)
    }
}