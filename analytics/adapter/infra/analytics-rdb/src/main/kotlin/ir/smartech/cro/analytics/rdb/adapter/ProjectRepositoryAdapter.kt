package ir.smartech.cro.analytics.rdb.adapter

import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import ir.smartech.cro.analytics.domain.project.spi.ProjectRepository
import ir.smartech.cro.analytics.rdb.mapper.ProjectMapper
import ir.smartech.cro.analytics.rdb.repository.JpaProjectRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProjectRepositoryAdapter(private val repo: JpaProjectRepository, val projectMapper: ProjectMapper) :
    ProjectRepository {
    override fun save(entity: Project?): Project? {
        if (entity == null) return null
        val jpaProject = projectMapper.toDestination(entity)
        val result = repo.save(jpaProject)
        return projectMapper.toSource(result)
    }

    override fun findById(id: Int): Project? {
        val project = repo.findById(id)
            .orElseThrow { ResponseException(ErrorCodes.NOT_FOUND, "the project with $id id not found") }
        return projectMapper.toSource(project)
    }
}