package ir.smartech.cro.analytics.adapter.postgres.mapper.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.funnel.JpaStep
import ir.smartech.cro.analytics.adapter.postgres.mapper.Mapper
import ir.smartech.cro.analytics.adapter.postgres.mapper.ProjectMapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step
import org.springframework.stereotype.Component

@Component
class StepMapper(private var projectMapper: ProjectMapper, private val stepConditionMapper: StepConditionMapper) :
    Mapper<Step, JpaStep> {
    override fun toSource(dto: Step?): JpaStep? {
        val funnelMapper = FunnelMapper(projectMapper, this)
        if (dto == null) return null
        return JpaStep().apply {
            eventName = dto.eventName
            seq = dto.seq
            funnel = funnelMapper.toSource(dto.funnel)
            jpaStepConditions = dto.conditions.map { stepConditionMapper.toSource(it)!! }.toCollection(ArrayList())
        }
    }

    override fun toDestination(dto: JpaStep?): Step? {
        val funnelMapper = FunnelMapper(projectMapper, this)
        if (dto == null) return null
        return Step().apply {
            eventName = dto.eventName
            seq = dto.seq
            funnel = funnelMapper.toDestination(dto.funnel)
            conditions = dto.jpaStepConditions.map { stepConditionMapper.toDestination(it)!! }
        }
    }
}