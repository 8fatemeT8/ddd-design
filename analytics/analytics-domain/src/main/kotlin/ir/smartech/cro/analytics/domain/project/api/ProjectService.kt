package ir.smartech.cro.analytics.domain.project.api

import ir.smartech.cro.analytics.domain.project.api.entity.Project
import ir.smartech.cro.analytics.domain.project.spi.ProjectRepository

class ProjectService(private val repo: ProjectRepository) {

    fun upsert(entity: Project?): Project? = repo.save(entity)

    fun getById(id: Int): Project? = repo.findById(id)
}