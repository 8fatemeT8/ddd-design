package ir.smartech.cro.analytics.domain.funnel.api.dto

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

open class StepConditionQueryBaseDto {
    var eventProperty: String? = null

    var eventPropertyType: PropertyType? = null

    var operator: Operator? = null

    var negate: Boolean? = null
}

data class StepConditionBetweenQueryDto(var min: String? = null, var max: String? = null) : StepConditionQueryBaseDto()
data class StepConditionSimpleQueryDto(var value: String? = null) : StepConditionQueryBaseDto()
data class StepConditionOneOfQueryDto(var values: List<String>? = null) : StepConditionQueryBaseDto()


