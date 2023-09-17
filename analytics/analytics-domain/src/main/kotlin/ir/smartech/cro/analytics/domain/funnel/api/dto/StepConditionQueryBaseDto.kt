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
 * this class uses for returning value of [Operator.BETWEEN] , [Operator.NOT_BETWEEN]
 */
data class StepConditionBetweenQueryDto(var min: String? = null, var max: String? = null) :
    StepConditionQueryBaseDto() {
    fun getCondition() =
        if (operator == Operator.BETWEEN)
            if (negate!!) "$eventProperty < '$min' and $eventProperty > '$max'"
            else "$eventProperty > '$min' and $eventProperty < '$max'"
        else
            if (negate!!) "$eventProperty > '$min' and $eventProperty < '$max'"
            else "$eventProperty < '$min' and $eventProperty > '$max'"
}

/**
 * this class uses for returning value of
 * [Operator.CONTAINS], [Operator.ENDS_WITH], [Operator.EQUAL], [Operator.STARTS_WITH], [Operator.GREATER_THAN_OR_EQUAL]
 */
data class StepConditionSimpleQueryDto(var value: String? = null) : StepConditionQueryBaseDto() {
    fun getCondition() =
        "${operator?.getFunctionName(negate!!)}($eventProperty , ${if (operator == Operator.CONTAINS) "'$value%'" else "'$value'"})"
}

/**
 * this class uses for returning value of [Operator.ONE_OF]
 */
data class StepConditionOneOfQueryDto(var values: List<String>? = null) : StepConditionQueryBaseDto() {
    fun getCondition() =
        if (negate!!)
            "in($eventProperty,[${values?.joinToString(",") { "'$it'" }}])"
        else "notIn($eventProperty,[${values?.joinToString(",") { "'$it'" }}])"
}


