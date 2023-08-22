package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.project.api.entity.Project
import ir.smartech.cro.analytics.domain.project.spi.ProjectRepository
import ir.smartech.cro.analytics.rdb.mapper.ProjectMapper
import ir.smartech.cro.analytics.rdb.repository.JpaProjectRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class ProjectRepositoryAdapter(private val repo: JpaProjectRepository, val projectMapper: ProjectMapper) :
    ProjectRepository {
    override fun save(entity: Project): Project {
        val jpaProject = projectMapper.toDestination(entity) ?: throw NotFoundException()
        val result = repo.save(jpaProject)
        return projectMapper.toSource(result)!!
    }
}