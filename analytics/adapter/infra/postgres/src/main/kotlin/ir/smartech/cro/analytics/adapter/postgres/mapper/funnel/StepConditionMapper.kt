package ir.smartech.cro.analytics.adapter.postgres.mapper.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.funnel.JpaStepCondition
import ir.smartech.cro.analytics.adapter.postgres.mapper.Mapper
import ir.smartech.cro.analytics.adapter.postgres.mapper.ProjectMapper
import ir.smartech.cro.analytics.adapter.postgres.mapper.PropertyMapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.StepCondition
import org.springframework.stereotype.Component

@Component
class StepConditionMapper(private val propertyMapper: PropertyMapper, private var projectMapper: ProjectMapper) :
    Mapper<StepCondition, JpaStepCondition> {
    override fun toSource(dto: StepCondition?): JpaStepCondition? {
        val stepMapper = StepMapper(projectMapper, this)
        if (dto == null) return null
        return JpaStepCondition().apply {
            funnelJpaStep = stepMapper.toSource(dto.funnelStep)
            property = propertyMapper.toSource(dto.property)
            operator = dto.operator
            value = dto.value
        }
    }

    override fun toDestination(dto: JpaStepCondition?): StepCondition? {
        val stepMapper = StepMapper(projectMapper, this)
        if (dto == null) return null
        return StepCondition().apply {
            funnelStep = stepMapper.toDestination(dto.funnelJpaStep)
            property = propertyMapper.toDestination(dto.property)
            operator = dto.operator
            value = dto.value
        }
    }
}