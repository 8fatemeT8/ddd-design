package ir.smartech.cro.analytics.rdb.mapper.event

import ir.smartech.cro.analytics.rdb.entity.event.JpaProperty
import ir.smartech.cro.analytics.domain.property.api.entity.Property
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class PropertyMapper(private val modelMapper: ModelMapper) : Mapper<Property, JpaProperty> {
    override fun toDestination(dto: Property?): JpaProperty? = modelMapper.map(dto, JpaProperty::class.java)
    override fun toSource(dto: JpaProperty?): Property? = modelMapper.map(dto, Property::class.java)
}