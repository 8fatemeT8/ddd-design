package ir.smartech.cro.analytics.rdb.mapper.funnel

import ir.smartech.cro.analytics.rdb.entity.funnel.JpaStep
import ir.smartech.cro.analytics.rdb.mapper.Mapper
import ir.smartech.cro.analytics.domain.funnel.api.entity.Step
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class StepMapper(private var modelMapper: ModelMapper) : Mapper<Step, JpaStep> {
    override fun toSource(dto: Step?): JpaStep? = modelMapper.map(dto, JpaStep::class.java)
    override fun toDestination(dto: JpaStep?): Step? = modelMapper.map(dto, Step::class.java)
}