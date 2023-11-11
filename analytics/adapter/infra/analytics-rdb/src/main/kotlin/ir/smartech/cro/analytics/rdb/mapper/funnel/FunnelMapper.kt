package ir.smartech.cro.analytics.rdb.mapper.funnel

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaFunnel
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

/**
 * this class maps funnel from domain to entity and vice versa
 * toDestination : from Funnel to JpaFunnel
 * toSource : from JpaFunnel to funnel
 */
@Component
class FunnelMapper(private val modelMapper: ModelMapper) : Mapper<Funnel, JpaFunnel> {
    override fun toDestination(dto: Funnel): JpaFunnel = modelMapper.map(dto, JpaFunnel::class.java)
    override fun toSource(dto: JpaFunnel): Funnel = modelMapper.map(dto, Funnel::class.java)
}