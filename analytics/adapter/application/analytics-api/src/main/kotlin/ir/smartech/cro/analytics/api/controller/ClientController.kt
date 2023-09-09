package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.ClientCreateDto
import ir.smartech.cro.analytics.api.dto.ClientViewDto
import ir.smartech.cro.analytics.api.mapper.MapperInline
import ir.smartech.cro.analytics.domain.client.api.ClientService
import ir.smartech.cro.analytics.domain.client.api.entity.Client
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * ClientController with create api
 */
@RestController
@RequestMapping("/api/web/analytics/client")
class ClientController(private val service: ClientService, private val mapper: MapperInline) {

    @PostMapping
    fun create(@RequestBody dto: ClientCreateDto): ResponseEntity<*> {
        val entity = mapper.map<Client, ClientCreateDto>(dto)
        val saved = service.upsert(entity)
        val result = mapper.map<ClientViewDto, Client>(saved)
        return ResponseEntity.ok(result)
    }
}