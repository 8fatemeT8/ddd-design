package ir.smartech.cro.analytics.adapter.postgres.mapper.funnel

import ir.smartech.cro.analytics.adapter.postgres.entity.funnel.JpaStepCondition
import ir.smartech.cro.analytics.adapter.postgres.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.StepCondition
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class StepConditionMapper(private val modelMapper: ModelMapper) : Mapper<StepCondition, JpaStepCondition> {
    override fun toSource(dto: StepCondition?): JpaStepCondition? = modelMapper.map(dto, JpaStepCondition::class.java)
    override fun toDestination(dto: JpaStepCondition?): StepCondition? = modelMapper.map(dto, StepCondition::class.java)
}