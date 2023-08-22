package ir.smartech.cro.analytics.domain.property.spi

import ir.smartech.cro.analytics.domain.property.api.entity.Property

interface PropertyRepository {
    fun save(entity: Property): Property
    fun saveAll(entities: List<Property>): List<Property>
    fun findAllByEventIdAndProjectId(eventId: Int, projectId: Int): List<Property>
}