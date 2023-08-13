package ir.smartech.cro.analytics.adapter.userapi.controller

import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelCreateDto
import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelEditDto
import ir.smartech.cro.analytics.adapter.userapi.mapper.FunnelMapper
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(var funnelService: FunnelService) {
    @PostMapping
    fun create(@RequestBody dto: FunnelCreateDto): ResponseEntity<*> {
        val entity = FunnelMapper.toFunnel(dto)
        val saved = funnelService.upsert(entity)
        val response = FunnelMapper.toFunnelViewDto(saved)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(@RequestBody dto: FunnelEditDto): ResponseEntity<*> {
        val entity = FunnelMapper.toFunnel(dto)
        val saved = funnelService.upsert(entity)
        val response = FunnelMapper.toFunnelViewDto(saved)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<*> {
        val response = FunnelMapper.toFunnelViewDto(funnelService.findById(id))
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getAll(): ResponseEntity<*> {
        val response = FunnelMapper.toFunnelListDto(funnelService.findAll())
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<*> {
        return ResponseEntity.ok(funnelService.deleteById(id))
    }
}