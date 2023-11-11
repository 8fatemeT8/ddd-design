package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.*
import ir.smartech.cro.analytics.api.mapper.BaseMapper
import ir.smartech.cro.analytics.domain.common.api.BaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * the BaseController has crud api. It just needs some types :
 *  TEntity (refers to entity) ,
 *  TCreate (refers to entity CreateDto it must extend from BaseCreateDto) ,
 *  TEdit (refers to entity EditDto it must extend from BaseEditDto) ,
 *  TView (refers to entity ViewDto it must extend from BaseViewDto) ,
 *  TList (refers to entity ListDto it must extend from BaseListDto) ,
 *  TMapper (refers to entity Mapper it must extend from BaseMapper) ,
 *  TService (refers to entity Service it must extend from BaseService)
 */
abstract class BaseController<TEntity, TCreate : BaseCreateDto, TEdit : BaseEditDto, TView : BaseViewDto, TList : BaseListDto, TMapper :
BaseMapper<TEntity, TCreate, TEdit, TView, TList>, TService : BaseService<TEntity>>(
    private val mapper: TMapper,
    private val service: TService
) {

    /**
     * this method calls before save object (in create and update apis)
     */
    abstract fun beforeSave(entity: TEntity, clientId: Int)


    /**
     * create object
     * @param dto get TCreate from body
     * @param clientId get this field from Header
     * @return TView object
     */
    @PostMapping
    fun create(@RequestBody dto: TCreate?, @RequestHeader("Client-id") clientId: Int): ResponseEntity<*> {
        val entity = mapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        beforeSave(entity, clientId)
        val saved = service.upsert(entity)
        val response = mapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    /**
     * update object
     * @param dto get TEdit from body
     * @param clientId get this field from Header
     * @return TView object
     */
    @PutMapping
    fun update(@RequestBody dto: TEdit?, @RequestHeader("Client-id") clientId: Int): ResponseEntity<*> {
        val entity = mapper.toEntity(dto) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("this object not found")
        beforeSave(entity, clientId)
        val saved = service.upsert(entity)
        val response = mapper.toView(saved)
        return ResponseEntity.ok(response)
    }

    /**
     * get object by id
     * @param id get id in PathVariable
     * @param clientId get this field from Header
     * @return TView object or return notFoundException
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int, @RequestHeader("Client-id") clientId: Int): ResponseEntity<*> {
        val response = mapper.toView(service.findByIdAndClientId(id, clientId))
        return ResponseEntity.ok(response)
    }

    /**
     * get all objects as a list
     * @param clientId get this field from Header
     * @return TList object or return empty list
     */
    @GetMapping
    fun getAll(@RequestHeader("Client-id") clientId: Int): ResponseEntity<*> {
        val dbResult = service.findAllClientId(clientId)
        val response = mapper.toList(dbResult)
        return ResponseEntity.ok(response)
    }

    /**
     * delete object by id
     * @param clientId get this field from Header
     * @return boolean
     */
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int, @RequestHeader("Client-id") clientId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(service.deleteById(id, clientId))
    }
}