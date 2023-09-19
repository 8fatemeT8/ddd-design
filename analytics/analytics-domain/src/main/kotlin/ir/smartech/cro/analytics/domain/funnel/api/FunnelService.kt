package ir.smartech.cro.analytics.domain.funnel.api

import ir.smartech.cro.analytics.domain.common.api.BaseService
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryResponse
import ir.smartech.cro.analytics.domain.funnel.api.dto.SegmentFunnelQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step
import ir.smartech.cro.analytics.domain.funnel.spi.ClickhouseRepository
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import java.util.Date

/**
 * Service provide the [Funnel] basic logics such as crud
 */
class FunnelService(
    private val funnelRepository: FunnelRepository,
    private val clickhouseRepository: ClickhouseRepository
) : BaseService<Funnel> {
    override fun upsert(entity: Funnel?): Funnel? {
        return funnelRepository.save(entity)
    }

    override fun findById(id: Int): Funnel? {
        return funnelRepository.findById(id)
    }

    override fun findByIdAndClientId(id: Int, clientId: Int): Funnel? {
        return funnelRepository.findByIdAndClientId(id, clientId)
    }

    override fun findAll(): List<Funnel?> {
        return funnelRepository.findAll().toList()
    }

    override fun findAllClientId(clientId: Int): List<Funnel?> {
        return funnelRepository.findAllByClientId(clientId).toList()
    }

    override fun deleteById(id: Int, clientId: Int): Boolean {
        funnelRepository.deleteById(id, clientId)
        return true
    }

    override fun delete(entity: Funnel?, clientId: Int): Boolean {
        funnelRepository.delete(entity, clientId)
        return true
    }

    fun findAllByContainsName(name: String, pageable: Any, clientId: Int): Any {
        return funnelRepository.findAllByNameList(name, pageable, clientId)
    }

    fun getFunnelQuery(
        id: Int, clientId: Int, completionTime: Long, startDate: Date?, endDate: Date?
    ): List<FunnelQueryResponse> {
        val funnel = findByIdAndClientId(id, clientId)
        val response = clickhouseRepository.getFunnelQueryById(funnel!!, completionTime, startDate?.time, endDate?.time)
        val result = funnel.steps.map {
            FunnelQueryResponse(
                it?.stepNumber,
                response.filter { it2 -> it2.level!! >= it?.stepNumber!! }
                    .sumOf { it2 -> it2.result!! })
        }
        return result
    }

    fun getFunnelQuerySplitBy(
        id: Int, clientId: Int, completionTime: Long, splitBy: String, startDate: Date?, endDate: Date?
    ): List<FunnelQueryResponse> {
        val funnel = findByIdAndClientId(id, clientId)
        val response =
            clickhouseRepository.getFunnelSplitBy(funnel!!, completionTime, splitBy, startDate?.time, endDate?.time)
        val groupBy = response.groupBy { it2 -> it2.splitValue }

        return groupBy.entries.map {
            if (it.value.size != funnel.steps.size) {
                // TODO add missing FunnelQueryDto
            }
            it.value.map { it2 ->
                FunnelQueryResponse(
                    it2.level,
                    it.value.filter { it3 -> it3.level!! >= it2.level!! }.sumOf { it3 -> it3.result!! },
                    it.key
                )
            }
        }.flatten().sortedBy { it.stepNumber }
    }

    fun getFunnelQuerySegment(
        id: Int, clientId: Int, completionTime: Long, steps: List<Step>, startDate: Date?, endDate: Date?
    ): List<SegmentFunnelQueryDto> {
        val funnel = findByIdAndClientId(id, clientId)
        return clickhouseRepository.getFunnelSegment(funnel!!, completionTime, steps, startDate?.time, endDate?.time)
    }
}