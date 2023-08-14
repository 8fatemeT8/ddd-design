package ir.smartech.cro.analytics.adapter.userapi.mapper

import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelCreateDto
import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelEditDto
import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelListDto
import ir.smartech.cro.analytics.adapter.userapi.dto.FunnelViewDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel

interface FunnelMapper {
    companion object {
        fun toFunnel(dto: FunnelCreateDto): Funnel {
            return Funnel().apply {
                name = dto.name
            }
        }

        fun toFunnel(dto: FunnelEditDto): Funnel {
            return Funnel().apply {
                name = dto.name
            }
        }

        fun toFunnelViewDto(funnel: Funnel?): FunnelViewDto {
            return FunnelViewDto(funnel?.name!!)
        }

        fun toFunnelListDto(funnel: List<Funnel?>): List<FunnelListDto> {
            return funnel.map {
                FunnelListDto(it?.name!!)
            }
        }
    }
}