package ir.smartech.cro.analytics.rdb.mapper.funnel

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaStep
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

/**
 * this class maps step from domain to entity and vice versa
 * toDestination : from Step to JpaStep
 * toSource : from JpaStep to Step
 */
@Component
class StepMapper(private var modelMapper: ModelMapper) : Mapper<Step, JpaStep> {
    override fun toDestination(dto: Step): JpaStep = modelMapper.map(dto, JpaStep::class.java)
    override fun toSource(dto: JpaStep): Step = modelMapper.map(dto, Step::class.java)
}