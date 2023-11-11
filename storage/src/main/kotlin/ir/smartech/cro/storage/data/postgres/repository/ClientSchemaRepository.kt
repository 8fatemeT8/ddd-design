package ir.smartech.cro.storage.data.postgres.repository

import ir.smartech.cro.storage.data.postgres.entity.ClientSchema
import org.springframework.data.jpa.repository.JpaRepository

interface ClientSchemaRepository : JpaRepository<ClientSchema, Int> {
    fun findByClientId(id: Int): ClientSchema
}