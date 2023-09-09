package ir.smartech.cro.analytics.api.mapper

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

/**
 * this method maps input param type to output type
 */
@Component
class MapperInline(val modelMapper: ModelMapper) {
    final inline fun <reified T, E> map(dto: E?): T = modelMapper.map(dto, T::class.java)
}