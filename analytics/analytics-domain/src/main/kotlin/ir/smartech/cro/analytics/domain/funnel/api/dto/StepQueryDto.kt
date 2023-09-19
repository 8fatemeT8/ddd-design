package ir.smartech.cro.analytics.domain.funnel.api.dto


/**
 * this class return the required fields in funnel query
 */
class StepQueryDto {
    var conditions: List<StepConditionQueryBaseDto?>? = arrayListOf()

    var stepNumber: Long? = null

    var eventName: String? = null

    fun getStepQuery() = "event_name = '$eventName' ${
        if (conditions?.isNotEmpty() == true)
            conditions?.let {
                "and ${
                    it.joinToString(" and ") { it2 ->
                        when (it2) {
                            is StepConditionSimpleQueryDto -> it2.getCondition()
                            is StepConditionBetweenQueryDto -> it2.getCondition()
                            else -> (it2 as StepConditionOneOfQueryDto).getCondition()
                        }
                    }
                }"
            }
        else ""
    }"
}