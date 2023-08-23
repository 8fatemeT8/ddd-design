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
import ir.smartech.cro.analytics.domain.project.api.ProjectService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(
    funnelService: FunnelService,
    apiFunnelMapper: ApiFunnelMapper,
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
}