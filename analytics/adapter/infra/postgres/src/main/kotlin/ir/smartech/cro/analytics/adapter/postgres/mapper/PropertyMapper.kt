package ir.smartech.cro.analytics.adapter.postgres.mapper

import ir.smartech.cro.analytics.adapter.postgres.entity.JpaProperty
import ir.smartech.cro.analytics.domain.common.api.entity.Property
import org.springframework.stereotype.Component

@Component
class PropertyMapper(
    private val projectMapper: ProjectMapper
) : Mapper<Property, JpaProperty> {
    override fun toSource(dto: Property?): JpaProperty? {
        val that = this
        if (dto == null) return null
        return JpaProperty().apply {
            project = projectMapper.toSource(dto.project)
            name = dto.name
            propertyType = dto.propertyType
            eventType = dto.eventType
            parent = that.toSource(dto.parent)
        }
    }

    override fun toDestination(dto: JpaProperty?): Property? {
        val that = this
        if (dto == null) return null
        return Property().apply {
            project = projectMapper.toDestination(dto.project)
            name = dto.name
            propertyType = dto.propertyType
            eventType = dto.eventType
            parent = that.toDestination(dto.parent)
        }
    }
}