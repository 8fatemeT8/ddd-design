package ir.smartech.cro.analytics.adapter.userapi.controller

import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelCreateDto
import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelEditDto
import ir.smartech.cro.analytics.adapter.userapi.mapper.ApiFunnelMapper
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(var funnelService: FunnelService, var apiFunnelMapper: ApiFunnelMapper) {
    @PostMapping
    fun create(@RequestBody dto: FunnelCreateDto): ResponseEntity<*> {
        val entity = apiFunnelMapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        val saved = funnelService.upsert(entity)
        val response = apiFunnelMapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(@RequestBody dto: FunnelEditDto): ResponseEntity<*> {
        val entity = apiFunnelMapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        val saved = funnelService.upsert(entity)
        val response = apiFunnelMapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = apiFunnelMapper.toView(funnelService.findById(id))
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = apiFunnelMapper.toList(funnelService.findAll())
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<*> {
        return ResponseEntity.ok(funnelService.deleteById(id))
    }
}