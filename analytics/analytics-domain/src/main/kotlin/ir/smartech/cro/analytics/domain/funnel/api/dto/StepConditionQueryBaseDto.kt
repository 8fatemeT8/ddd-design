package ir.smartech.cro.analytics.domain.funnel.api.dto

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

/**
 * this is the base of condition classes
 * the conditions separate based on [Operator]
 */
open class StepConditionQueryBaseDto {
    var eventProperty: String? = null

    var eventPropertyType: PropertyType? = null

    var operator: Operator? = null

    var negate: Boolean? = null
}

/**
 * this class uses for returning value of [Operator.BETWEEN]
 */
data class StepConditionBetweenQueryDto(var min: String? = null, var max: String? = null) : StepConditionQueryBaseDto()

/**
 * this class uses for returning value of
 * [Operator.CONTAINS], [Operator.ENDS_WITH], [Operator.EQUAL], [Operator.STARTS_WITH], [Operator.GREATER_THAN_OR_EQUAL]
 */
data class StepConditionSimpleQueryDto(var value: String? = null) : StepConditionQueryBaseDto()

/**
 * this class uses for returning value of [Operator.ONE_OF]
 */
data class StepConditionOneOfQueryDto(var values: List<String>? = null) : StepConditionQueryBaseDto()


