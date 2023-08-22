package ir.smartech.cro.analytics.rdb.mapper.funnel

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaFunnel
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class FunnelMapper(private val modelMapper: ModelMapper) : Mapper<Funnel, JpaFunnel> {
    override fun toDestination(dto: Funnel?): JpaFunnel? = modelMapper.map(dto, JpaFunnel::class.java)
    override fun toSource(dto: JpaFunnel?): Funnel? = modelMapper.map(dto, Funnel::class.java)
}