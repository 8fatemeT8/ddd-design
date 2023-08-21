package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.dto.ProjectSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/cro/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun upsert(@RequestBody entity: User): ResponseEntity<Any> {
        val saved = userService.upsert(entity)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = userService.getById(id)
        return ResponseEntity.ok(response.get())
    }

    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = userService.getAll()
        return ResponseEntity.ok(response)
    }

    @PostMapping("/schema")
    fun setSchema(@RequestBody dto: ProjectSchemaDto): ResponseEntity<Any> {
        userService.setSchema(dto)
        return ResponseEntity.ok().build()
    }
}