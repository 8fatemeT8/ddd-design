package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.funnel.FunnelCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelEditDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelListDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelViewDto
import ir.smartech.cro.analytics.api.mapper.ApiFunnelMapper
import ir.smartech.cro.analytics.domain.common.api.utils.ErrorCodes
import ir.smartech.cro.analytics.domain.common.api.utils.ResponseException
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.api.entity.toQueryDto
import ir.smartech.cro.analytics.domain.project.api.ProjectService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(
    private val funnelService: FunnelService,
    private val apiFunnelMapper: ApiFunnelMapper,
    private val projectService: ProjectService
) :
    BaseController<Funnel, FunnelCreateDto, FunnelEditDto, FunnelViewDto, FunnelListDto, ApiFunnelMapper, FunnelService>(
        apiFunnelMapper,
        funnelService
    ) {
    override fun beforeSave(entity: Funnel, projectId: Int) {
        val project = projectService.getById(projectId) ?: throw ResponseException(
            ErrorCodes.NOT_FOUND,
            "the project wit $projectId id not found"
        )
        entity.project = project
    }

    @GetMapping("contains/{name}")
    fun getAllByContains(
        @PathVariable("name") name: String,
        pageable: Pageable,
        @RequestHeader("Project-Id") projectId: Int
    ): ResponseEntity<*> {
        val result = funnelService.findAllByContainsName(name, pageable, projectId) as Page<Funnel?>
        val response = PageImpl(apiFunnelMapper.toList(result.content)!!, pageable, result.totalPages.toLong())
        return ResponseEntity.ok(response)
    }


    @GetMapping("/{id}/query")
    fun query(@PathVariable("id") id: Int?, @RequestHeader("Project-Id") projectId: Int): ResponseEntity<*> {
        return ResponseEntity.ok(funnelService.findByIdAndProjectId(id!!, projectId)?.toQueryDto())
    }
}