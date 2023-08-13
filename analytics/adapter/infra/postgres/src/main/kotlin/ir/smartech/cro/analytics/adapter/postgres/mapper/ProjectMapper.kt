package ir.smartech.cro.analytics.adapter.postgres.mapper

import ir.smartech.cro.analytics.adapter.postgres.entity.JpaProject
import ir.smartech.cro.analytics.domain.common.api.entity.Project
import org.springframework.stereotype.Component

@Component
class ProjectMapper : Mapper<Project, JpaProject> {
    override fun toSource(dto: Project?): JpaProject? {
        val propertyMapper = PropertyMapper(this)
        if (dto == null) return null
        return JpaProject().apply {
            id = dto.id
            name = dto.name
            description = dto.description
            enabled = dto.enabled
            jpaProperties = dto.properties.map { propertyMapper.toSource(it) }.toCollection(ArrayList())
        }
    }

    override fun toDestination(dto: JpaProject?): Project? {
        val propertyMapper = PropertyMapper(this)
        if (dto == null) return null
        return Project().apply {
            id = dto.id
            name = dto.name
            description = dto.description
            enabled = dto.enabled
            properties = dto.jpaProperties.map { propertyMapper.toDestination(it) }
        }
    }
}