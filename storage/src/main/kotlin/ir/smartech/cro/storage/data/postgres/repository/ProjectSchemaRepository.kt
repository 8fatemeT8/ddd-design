package ir.smartech.cro.storage.data.postgres.repository

import ir.smartech.cro.storage.data.postgres.entity.ProjectSchema
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectSchemaRepository : JpaRepository<ProjectSchema, Int> {
}