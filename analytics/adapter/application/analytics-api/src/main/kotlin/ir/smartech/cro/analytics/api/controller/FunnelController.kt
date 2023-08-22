package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.funnel.FunnelCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelEditDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelListDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelViewDto
import ir.smartech.cro.analytics.api.mapper.ApiFunnelMapper
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.project.api.entity.Project
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/analytics/funnel")
class FunnelController(private var funnelService: FunnelService, private var apiFunnelMapper: ApiFunnelMapper) :
    BaseController<Funnel, FunnelCreateDto, FunnelEditDto, FunnelViewDto, FunnelListDto, ApiFunnelMapper, FunnelService>(
        apiFunnelMapper,
        funnelService
    ) {
    override fun beforeSave(entity: Funnel, projectId: Int) {
        entity.project = Project().apply {
            id = projectId
        }
    }
}