package ir.smartech.cro.analytics.domain.common.api.enums


enum class Operator(var key: String?) {
    EQUAL("equal"),
    NOT_EQUAL("not_equal"),
    GREATER_THAN("greater_than"),
    LESS_THAN("less_than"),
    BETWEEN("between"),
    NOT_BETWEEN("not_between"),
    CONTAINS("contains"),
    DOES_NOT_CONTAIN("does_not_contain"),
    STARTS_WITH("starts_with"),
    ENDS_WITH("ends_with"),
    IS_NOT_EMPTY("is_not_empty"),
    IS_EMPTY("is_empty"),
    EXISTS("exists"),
    NOT_EXISTS("not_exists"),
    LESS_THAN_OR_EQUAL("less_than_or_equal"),
    GREATER_THAN_OR_EQUAL("greater_than_or_equal"),
    NULL("is_null"),
    NOT_NULL("is_not_null");
}