package ir.smartech.cro.analytics.rdb.mapper.funnel

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaStepCondition
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.StepCondition
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

/**
 * this class maps stepCondition from domain to entity and vice versa
 * toDestination : from StepCondition to JpaStepCondition
 * toSource : from JpaStepCondition to StepCondition
 */
@Component
class StepConditionMapper(private val modelMapper: ModelMapper) : Mapper<StepCondition, JpaStepCondition> {
    override fun toDestination(dto: StepCondition): JpaStepCondition = modelMapper.map(dto, JpaStepCondition::class.java)
    override fun toSource(dto: JpaStepCondition): StepCondition = modelMapper.map(dto, StepCondition::class.java)
}