package ir.smartech.cro.storage.data.postgres.repository

import ir.smartech.cro.storage.data.postgres.entity.ProductSchema
import ir.smartech.cro.storage.data.postgres.entity.ProductSchemaId
import org.springframework.data.jpa.repository.JpaRepository

interface ProductSchemaRepository : JpaRepository<ProductSchema, ProductSchemaId> {
}