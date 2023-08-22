package ir.smartech.cro.analytics.api.mapper

import ir.smartech.cro.analytics.api.dto.event.EventCreateDto
import ir.smartech.cro.analytics.api.dto.event.EventEditDto
import ir.smartech.cro.analytics.api.dto.event.EventListDto
import ir.smartech.cro.analytics.api.dto.event.EventViewDto
import ir.smartech.cro.analytics.domain.event.api.entity.Event
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ApiEventMapper(val modelMapper: ModelMapper) :
    BaseMapper<Event, EventCreateDto, EventEditDto, EventViewDto, EventListDto> {
    override fun toEntity(create: EventCreateDto?): Event? = modelMapper.map(create, Event::class.java)

    override fun createToListEntity(create: List<EventCreateDto?>?): List<Event?>? =
        create?.map { modelMapper.map(it, Event::class.java) }

    override fun toEntity(edit: EventEditDto?): Event? = modelMapper.map(edit, Event::class.java)

    override fun editToListEntity(edit: List<EventEditDto?>?): List<Event?>? =
        edit?.map { modelMapper.map(it, Event::class.java) }

    override fun toView(entity: Event?): EventViewDto? = modelMapper.map(entity, EventViewDto::class.java)

    override fun toView(entity: List<Event?>?): List<EventViewDto?>? =
        entity?.map { modelMapper.map(it, EventViewDto::class.java) }

    override fun toList(entity: Event?): EventListDto? = modelMapper.map(entity, EventListDto::class.java)

    override fun toList(entity: List<Event?>?): List<EventListDto?>? =
        entity?.map { modelMapper.map(it, EventListDto::class.java) }

}