package ir.smartech.cro.analytics.adapter.postgres.mapper

import ir.smartech.cro.analytics.adapter.postgres.entity.JpaProperty
import ir.smartech.cro.analytics.domain.common.api.entity.Property
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class PropertyMapper(private val modelMapper: ModelMapper) : Mapper<Property, JpaProperty> {
    override fun toSource(dto: Property?): JpaProperty? = modelMapper.map(dto, JpaProperty::class.java)
    override fun toDestination(dto: JpaProperty?): Property? = modelMapper.map(dto, Property::class.java)
}