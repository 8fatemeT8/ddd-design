package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.ProjectCreateDto
import ir.smartech.cro.analytics.api.dto.ProjectViewDto
import ir.smartech.cro.analytics.api.mapper.MapperInline
import ir.smartech.cro.analytics.domain.project.api.ProjectService
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/analytics/project")
class ProjectController(private val service: ProjectService, private val mapper: MapperInline) {

    @PostMapping
    fun create(@RequestBody dto: ProjectCreateDto): ResponseEntity<*> {
        val entity = mapper.map<Project, ProjectCreateDto>(dto)
        val saved = service.upsert(entity)
        val result = mapper.map<ProjectViewDto, Project>(saved)
        return ResponseEntity.ok(result)
    }
}