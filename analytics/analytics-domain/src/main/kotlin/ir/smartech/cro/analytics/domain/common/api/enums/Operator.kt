package ir.smartech.cro.analytics.domain.common.api.enums

import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionBetweenQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionOneOfQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionQueryBaseDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionSimpleQueryDto


/**
 * all acceptable operator that the client can choose in analytics conditions
 * because of the operator value difference we decided to using some different dto and implementing parser method
 * functionName : the name of clickhouse function for the operator
 * negateFunctionName : the name of clickhouse function for not of operator
 * */
enum class Operator(private val functionName: String? = null, private val negateFunctionName: String? = null) {
    GREATER_THAN_OR_EQUAL("greaterOrEquals", "lessOrEquals") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    NOT_BETWEEN {
        override fun parser(value: String?): StepConditionBetweenQueryDto? {
            val data = value?.split(",") ?: return null
            return StepConditionBetweenQueryDto(data[0], data[1])
        }
    },
    EQUAL("equals", "notEquals") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    //GENERAL
    EQUAL_TO("equals", "notEquals") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    IS_NOT_EMPTY("notEmpty", "empty") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    // TODO negate function is wrong
    //STRING
    STARTS_WITH("startsWith", "notStartsWith") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    // TODO negate function is wrong
    ENDS_WITH("endsWith", "notEndsWith") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    MATCHES_REGEX {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    CONTAINS("like", "notLike") {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    // STRING_NUMERIC
    ONE_OF {
        override fun parser(value: String?): StepConditionOneOfQueryDto? {
            val data = value?.split(",") ?: return null
            return StepConditionOneOfQueryDto(data)
        }
    },

    // NUMERIC_DATE
    BETWEEN {
        override fun parser(value: String?): StepConditionBetweenQueryDto? {
            val data = value?.split(",") ?: return null
            return StepConditionBetweenQueryDto(data[0], data[1])
        }
    };


    abstract fun parser(value: String?): StepConditionQueryBaseDto?
    fun getFunctionName(negate: Boolean) = if (negate) negateFunctionName else functionName
}