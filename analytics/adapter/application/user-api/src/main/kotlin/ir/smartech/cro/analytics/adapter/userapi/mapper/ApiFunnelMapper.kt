package ir.smartech.cro.analytics.adapter.userapi.mapper

import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelCreateDto
import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelEditDto
import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelListDto
import ir.smartech.cro.analytics.adapter.userapi.dto.funnel.FunnelViewDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ApiFunnelMapper(private val modelMapper: ModelMapper) :
    BaseMapper<Funnel, FunnelCreateDto, FunnelEditDto, FunnelViewDto, FunnelListDto> {
    override fun toEntity(create: FunnelCreateDto?): Funnel? = modelMapper.map(create, Funnel::class.java)

    override fun createToListEntity(create: List<FunnelCreateDto?>?): List<Funnel?>? =
        create?.map { modelMapper.map(it, Funnel::class.java) }

    override fun toEntity(edit: FunnelEditDto?): Funnel? = modelMapper.map(edit, Funnel::class.java)

    override fun editToListEntity(edit: List<FunnelEditDto?>?): List<Funnel?>? =
        edit?.map { modelMapper.map(it, Funnel::class.java) }

    override fun toView(entity: Funnel?): FunnelViewDto? = modelMapper.map(entity, FunnelViewDto::class.java)

    override fun toView(entity: List<Funnel?>?): List<FunnelViewDto?>? =
        entity?.map { modelMapper.map(it, FunnelViewDto::class.java) }

    override fun toList(entity: Funnel?): FunnelListDto? = modelMapper.map(entity, FunnelListDto::class.java)

    override fun toList(entity: List<Funnel?>?): List<FunnelListDto?>? =
        entity?.map { modelMapper.map(it, FunnelListDto::class.java) }
}