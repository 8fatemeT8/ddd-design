package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.ReturnType
import ir.smartech.cro.storage.data.postgres.dto.ProductSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.Product
import ir.smartech.cro.storage.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/product")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun upsert(@RequestBody entity: Product): ResponseEntity<*> {
        val saved = productService.upsert(entity)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = productService.getById(id)
        return ResponseEntity.ok(response.get())
    }

    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = productService.getAll()
        return ResponseEntity.ok(response)
    }

    @PostMapping("/schema")
    fun setSchema(@RequestBody dto: ProductSchemaDto): ResponseEntity<Any> {
        productService.setSchema(dto)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/return-type")
    fun getReturnTypes(): ResponseEntity<List<String>> = ResponseEntity.ok(ReturnType.values().map { it.toString() })
}