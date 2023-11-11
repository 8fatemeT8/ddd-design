package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.dto.ClientSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.Client
import ir.smartech.cro.storage.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * this controller has [Client] cruds
 */
@RestController
@RequestMapping("/api/web/cro/client")
class ClientController(private val clientService: ClientService) {

    /**
     * create and update client
     */
    @PostMapping
    fun upsert(@RequestBody entity: Client): ResponseEntity<Any> {
        val saved = clientService.upsert(entity)
        return ResponseEntity.ok(saved)
    }

    /**
     * get client by id
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = clientService.getById(id)
        return ResponseEntity.ok(response.get())
    }

    /**
     * get all clients as a list
     */
    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = clientService.getAll()
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
    fun setSchema(@RequestBody dto: ClientSchemaDto): ResponseEntity<Any> {
        clientService.setSchema(dto)
        return ResponseEntity.ok().build()
    }
}