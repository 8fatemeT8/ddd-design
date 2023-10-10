package ir.smartech.cro.analytics.api.utils

import ir.smartech.cro.analytics.domain.common.api.enums.Operator

class IntrackSegmentCreateDto {
    val conjunction = "AND"
    var name: String? = null
    val terms = arrayListOf(IntrackTermDto())
    val test = true //????????
    val type = "STATIC"
}

data class IntrackTermDto(
    val conjunction: String = "AND",
    val predicates: ArrayList<IntrackPredicateDto> = arrayListOf(IntrackPredicateDto())
)

data class IntrackPredicateDto(
    val attribute: Int = 39,
    val negate: Boolean = false,
    val operator: Operator = Operator.ONE_OF,
    var stringValue: String? = null,
    val type: String = "ATTRIBUTE"
)