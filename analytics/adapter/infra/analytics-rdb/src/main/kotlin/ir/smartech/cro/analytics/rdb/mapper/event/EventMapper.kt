package ir.smartech.cro.analytics.rdb.mapper.event

import ir.smartech.cro.analytics.domain.event.api.entity.Event
import ir.smartech.cro.analytics.rdb.entity.event.JpaEvent
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class EventMapper(private val modelMapper: ModelMapper) : Mapper<Event, JpaEvent> {
    override fun toDestination(dto: Event?): JpaEvent? = modelMapper.map(dto, JpaEvent::class.java)

    override fun toSource(dto: JpaEvent?): Event? = modelMapper.map(dto, Event::class.java)
}