package ir.smartech.cro.analytics.adapter.postgres.mapper.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.funnel.JpaFunnel
import ir.smartech.cro.analytics.adapter.postgres.mapper.Mapper
import ir.smartech.cro.analytics.adapter.postgres.mapper.ProjectMapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Funnel
import org.springframework.stereotype.Component

@Component
class FunnelMapper(
    private val projectMapper: ProjectMapper, private val stepMapper: StepMapper
) : Mapper<Funnel, JpaFunnel> {

    override fun toSource(dto: Funnel?): JpaFunnel? {
        val that = this
        if (dto == null) return null
        return JpaFunnel().apply {
            name = dto.name
            enable = dto.enable
            isDeleted = dto.isDeleted
            project = projectMapper.toSource(dto.project!!)
            startDate = dto.startDate
            expDate = dto.expDate
            eventType = dto.eventType
            predecessor = that.toSource(dto.predecessor)
            jpaSteps = dto.steps.map { stepMapper.toSource(it) }.toCollection(ArrayList())
        }
    }

    override fun toDestination(dto: JpaFunnel?): Funnel? {
        val that = this
        if (dto == null) return null
        val response = Funnel().apply {
            name = dto.name
            enable = dto.enable
            isDeleted = dto.isDeleted
            project = projectMapper.toDestination(dto.project!!)
            startDate = dto.startDate
            expDate = dto.expDate
            eventType = dto.eventType
            steps = dto.jpaSteps.map { stepMapper.toDestination(it) }
        }
        response.computedPredecessor(that.toDestination(dto.predecessor)!!)
        return response
    }
}