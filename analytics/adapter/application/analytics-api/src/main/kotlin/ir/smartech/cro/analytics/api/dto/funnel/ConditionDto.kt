package ir.smartech.cro.analytics.api.dto.funnel

import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType

/**
 * CreateDto of StepCondition domain
 */
data class ConditionCreateDto(
    var operator: Operator? = null,
    var value: String? = null,
    var negate: Boolean? = null,
    var eventProperty: String? = null,
    var eventPropertyType: PropertyType? = null
)

/**
 * ViewDto of StepCondition domain
 */
data class ConditionViewDto(
    var id: Int? = null,
    var operator: Operator? = null,
    var value: String? = null,
    var negate: Boolean? = null,
    var eventProperty: String? = null,
    var eventPropertyType: PropertyType? = null
)