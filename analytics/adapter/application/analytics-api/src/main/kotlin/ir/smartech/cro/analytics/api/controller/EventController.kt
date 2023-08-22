package ir.smartech.cro.analytics.api.controller

import ir.smartech.cro.analytics.api.dto.event.EventCreateDto
import ir.smartech.cro.analytics.api.dto.event.EventEditDto
import ir.smartech.cro.analytics.api.dto.event.EventListDto
import ir.smartech.cro.analytics.api.dto.event.EventViewDto
import ir.smartech.cro.analytics.api.mapper.ApiEventMapper
import ir.smartech.cro.analytics.domain.event.api.EventService
import org.springframework.web.bind.annotation.*
import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.domain.project.api.entity.Project


@RestController
@RequestMapping("/api/web/analytics/event")
class EventController(private val eventService: EventService, private val eventMapper: ApiEventMapper) :
    BaseController<Event, EventCreateDto, EventEditDto, EventViewDto, EventListDto, ApiEventMapper, EventService>(
        eventMapper,
        eventService
    ) {
    override fun beforeSave(entity: Event, projectId: Int) {
        entity.project = Project().apply {
            id = projectId
        }
    }
}