package ir.smartech.cro.analytics.domain.project.spi

import ir.smartech.cro.analytics.domain.project.api.entity.Project

interface ProjectRepository {
    fun save(entity: Project?): Project?

    fun findById(id: Int): Project?
}