package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.dto.ProjectSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * this controller has user cruds
 */
@RestController
@RequestMapping("/api/web/cro/user")
class UserController(private val userService: UserService) {

    /**
     * create and update user
     */
    @PostMapping
    fun upsert(@RequestBody entity: User): ResponseEntity<Any> {
        val saved = userService.upsert(entity)
        return ResponseEntity.ok(saved)
    }

    /**
     * get user by id
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = userService.getById(id)
        return ResponseEntity.ok(response.get())
    }

    /**
     * get all users as a list
     */
    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = userService.getAll()
        return ResponseEntity.ok(response)
    }

    /**
     * saving the client's data format
     * like :
    `{
    "data": {
    "appVersion": "STRING",
    "deviceType": "NUMBER",
    "eventParam": "JSON",
    "active": "BOOLEAN"
    }
    }`
     */
    @PostMapping("/schema")
    fun setSchema(@RequestBody dto: ProjectSchemaDto): ResponseEntity<Any> {
        userService.setSchema(dto)
        return ResponseEntity.ok().build()
    }
}