package ir.smartech.cro.analytics.adapter.postgres.mapper

import ir.smartech.cro.analytics.adapter.postgres.entity.JpaProject
import ir.smartech.cro.analytics.domain.common.api.entity.Project
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ProjectMapper(val modelMapper: ModelMapper) : Mapper<Project, JpaProject> {
    override fun toSource(dto: Project?): JpaProject? = modelMapper.map(dto, JpaProject::class.java)
    override fun toDestination(dto: JpaProject?): Project? = modelMapper.map(dto, Project::class.java)
}