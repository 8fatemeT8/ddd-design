package ir.smartech.cro.analytics.rdb.mapper

import ir.smartech.cro.analytics.rdb.entity.JpaProject
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ProjectMapper(private val modelMapper: ModelMapper) : Mapper<Project, JpaProject> {
    override fun toDestination(dto: Project?): JpaProject? = modelMapper.map(dto, JpaProject::class.java)
    override fun toSource(dto: JpaProject?): Project? = modelMapper.map(dto, Project::class.java)
}