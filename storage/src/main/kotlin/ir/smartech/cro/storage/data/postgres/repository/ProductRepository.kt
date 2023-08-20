package ir.smartech.cro.storage.data.postgres.repository

import ir.smartech.cro.storage.data.postgres.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int> {
}