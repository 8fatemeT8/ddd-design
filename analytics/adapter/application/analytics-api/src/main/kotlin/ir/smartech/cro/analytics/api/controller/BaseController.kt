package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.*
import ir.smartech.cro.analytics.api.mapper.BaseMapper
import ir.smartech.cro.analytics.domain.common.api.BaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class BaseController<TEntity, TCreate : BaseCreateDto, TEdit : BaseEditDto, TView : BaseViewDto, TList : BaseListDto, TMapper :
BaseMapper<TEntity, TCreate, TEdit, TView, TList>, TService : BaseService<TEntity>>(
    private val mapper: TMapper,
    private val service: TService
) {

    abstract fun beforeSave(entity: TEntity, projectId: Int)


    @PostMapping
    fun create(@RequestBody dto: TCreate, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val entity = mapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        beforeSave(entity, projectId)
        val saved = service.upsert(entity)
        val response = mapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(@RequestBody dto: TEdit, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val entity = mapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        beforeSave(entity, projectId)
        val saved = service.upsert(entity)
        val response = mapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val response = mapper.toView(service.findByIdAndProjectId(id, projectId))
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getAll(@RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        val dbResult = service.findAllProjectId(projectId)
        val response = mapper.toList(dbResult)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(service.deleteById(id,projectId))
    }
}