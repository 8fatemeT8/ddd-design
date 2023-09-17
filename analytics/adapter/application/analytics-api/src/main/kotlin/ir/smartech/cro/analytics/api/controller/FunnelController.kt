package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.funnel.*
import ir.smartech.cro.analytics.api.mapper.ApiFunnelMapper
import ir.smartech.cro.analytics.api.mapper.MapperInline
import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.client.api.ClientService
import ir.smartech.cro.analytics.domain.funnel.api.entity.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * this class contains query api and another api for getting data with some search in object name,
 * also it has crud apis
 */
@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(
    private val funnelService: FunnelService,
    private val apiFunnelMapper: ApiFunnelMapper,
    private val clientService: ClientService,
    private val mapperInline: MapperInline
) :
    BaseController<Funnel, FunnelCreateDto, FunnelEditDto, FunnelViewDto, FunnelListDto, ApiFunnelMapper, FunnelService>(
        apiFunnelMapper,
        funnelService
    ) {
    /**
     * validate the clientId and set on entity before persist
     */
    override fun beforeSave(entity: Funnel, clientId: Int) {
        val client = clientService.getById(clientId) ?: throw ResponseException(
            ErrorCodes.NOT_FOUND,
            "the client wit $clientId id not found"
        )
        entity.client = client
    }

    /**
     * this api returns funnels by search on funnel name
     * @param name we return funnels that contains this input
     * @param clientId also set predicate that just return Client's funnels
     * @return Page of FunnelListDto
     */
    @GetMapping("contains/{name}")
    fun getAllByContains(
        @PathVariable("name") name: String,
        pageable: Pageable,
        @RequestHeader("Client-id") clientId: Int
    ): ResponseEntity<*> {
        val result = funnelService.findAllByContainsName(name, pageable, clientId) as Page<Funnel?>
        val response = PageImpl(apiFunnelMapper.toList(result.content)!!, pageable, result.totalPages.toLong())
        return ResponseEntity.ok(response)
    }


    /**
     * returns normal funnel query
     */
    @GetMapping("/{id}/query")
    fun query(
        @PathVariable("id") id: Int?,
        @RequestHeader("Client-id") clientId: Int,
        @RequestBody dto: FunnelQueryRequest
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            funnelService.getFunnelQuery(id!!, clientId, dto.completionTime!!, dto.startDate, dto.endDate)
        )
    }


    /**
     * returns funnel query with split by input value
     */
    @GetMapping("/{id}/query-split")
    fun splitByQuery(
        @PathVariable("id") id: Int?,
        @RequestHeader("Client-id") clientId: Int,
        @RequestBody dto: FunnelQueryRequest
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            funnelService.getFunnelQuerySplitBy(
                id!!, clientId, dto.completionTime!!, dto.splitBy!!, dto.startDate, dto.endDate
            )
        )
    }


    /**
     * returns list of userId who dropped from firstStep and secondStep
     */
    @GetMapping("/{id}/query/segment")
    fun segmentQuery(
        @PathVariable("id") id: Int?,
        @RequestHeader("Client-id") clientId: Int,
        @RequestBody dto: FunnelQueryRequest
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            funnelService.getFunnelQuerySegment(
                id!!, clientId, dto.completionTime!!, dto.steps!!.map { mapperInline.map(it) },
                dto.startDate, dto.endDate
            )
        )
    }
}