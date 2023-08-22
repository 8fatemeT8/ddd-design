package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.ProjectViewDto
import ir.smartech.cro.analytics.api.dto.event.PropertyCreateDto
import ir.smartech.cro.analytics.api.dto.event.PropertyViewDto
import ir.smartech.cro.analytics.api.mapper.MapperInline
import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import ir.smartech.cro.analytics.domain.property.api.PropertyService
import ir.smartech.cro.analytics.domain.property.api.entity.Property
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/analytics/property")
class PropertyController(private val service: PropertyService, private val mapper: MapperInline) {
    @PostMapping
    fun create(@RequestBody dto: PropertyCreateDto, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val entity = mapper.map<Property, PropertyCreateDto>(dto)
        entity.project = setProject(projectId)
        val saved = service.upsert(entity)
        val result = mapper.map<ProjectViewDto, Property>(saved)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/batch")
    fun batchCreate(
        @RequestBody dtos: List<PropertyCreateDto>,
        @RequestHeader("Project-Id") projectId: Int
    ): ResponseEntity<*> {
        val entities = dtos.map {
            val result = mapper.map<Property, PropertyCreateDto>(it)
            result.project = setProject(projectId)
            result
        }
        val savedProperties = service.saveAll(entities)
        val result = savedProperties.map { mapper.map<ProjectViewDto, Property>(it) }
        return ResponseEntity.ok(result)
    }

    @PostMapping("/event/{id}")
    fun batchCreate(
        @RequestBody dtos: List<PropertyCreateDto>,
        @PathVariable("id") eventId: Int,
        @RequestHeader("Project-Id") projectId: Int
    ): ResponseEntity<*> {
        val entities = dtos.map {
            val data = mapper.map<Property, PropertyCreateDto>(it)
            data.event = setEvent(eventId)
            data.project = setProject(projectId)
            data
        }
        val savedProperties = service.saveAll(entities)
        val result = savedProperties.map { mapper.map<ProjectViewDto, Property>(it) }
        return ResponseEntity.ok(result)
    }

    @GetMapping("/event/{id}")
    fun getByEvent(@PathVariable("id") eventId: Int, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val dbResult = service.getPropertiesByEvent(eventId, projectId)
        val response = dbResult.map { mapper.map<PropertyViewDto, Property>(it) }
        return ResponseEntity.ok(response)
    }

    private fun setEvent(eventId: Int) =
        Event().apply {
            id = eventId
        }

    private fun setProject(projectId: Int) =
        Project().apply {
            id = projectId
        }
}