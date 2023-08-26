package ir.smartech.cro.analytics.domain.common.api.enums

import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionBetweenQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionOneOfQueryDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionQueryBaseDto
import ir.smartech.cro.analytics.domain.funnel.api.dto.StepConditionSimpleQueryDto


enum class Operator {
    GREATER_THAN_OR_EQUAL {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    NOT_BETWEEN {
        override fun parser(value: String?): StepConditionBetweenQueryDto?{
            val data = value?.split(",") ?: return null
            return StepConditionBetweenQueryDto(data[0], data[1])
        }
    },
    EQUAL {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    //GENERAL
    EQUAL_TO {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    IS_NOT_EMPTY {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },

    //STRING
    STARTS_WITH {
        override fun parser(value: String?): StepConditionSimpleQueryDto? {
            value ?: return null
            return StepConditionSimpleQueryDto(value)
        }
    },
    ENDS_WITH {
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
    CONTAINS {
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
}