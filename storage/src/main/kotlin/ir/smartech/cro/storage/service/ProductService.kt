package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.data.postgres.dto.ProductSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.Product
import ir.smartech.cro.storage.data.postgres.entity.ProductSchema
import ir.smartech.cro.storage.data.postgres.entity.ProductSchemaId
import ir.smartech.cro.storage.data.postgres.repository.ProductRepository
import ir.smartech.cro.storage.data.postgres.repository.ProductSchemaRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productSchemaRepository: ProductSchemaRepository
) {
    fun upsert(entity: Product): Product {
        return productRepository.save(entity)
    }

    fun getById(id: Int): Optional<Product> {
        return productRepository.findById(id)
    }

    fun getAll(): List<Product> {
        return productRepository.findAll()
    }

    fun setSchema(dto: ProductSchemaDto) {
        val product = getById(dto.id!!).get()
        val toBeSave = mapToEntity(dto, product)
        productSchemaRepository.save(toBeSave)
    }

    fun getSchema(product: Product, businessId: Int): ProductSchema =
        productSchemaRepository.getReferenceById(ProductSchemaId().apply {
            this.product = product
            this.businessId = businessId
        })

    private fun mapToEntity(dto: ProductSchemaDto, product: Product) =
        ProductSchema().apply {
            productSchemaId = ProductSchemaId().apply {
                businessId = dto.businessId
                this.product = product
            }
            data = dto.data
        }
}