package ir.smartech.cro.analytics.domain.common.api.enums


enum class Operator(var key: String?) {
//    @JsonProperty("equal")
    EQUAL("equal"),

//    @JsonProperty("not_equal")
    NOT_EQUAL("not_equal"),

//    @JsonProperty("greater")
    GREATER_THAN("greater_than"),

//    @JsonProperty("less")
    LESS_THAN("less_than"),

//    @JsonProperty("between")
    BETWEEN("between"),

//    @JsonProperty("not_between")
    NOT_BETWEEN("not_between"),

//    @JsonProperty("like")
    CONTAINS("contains"),

//    @JsonProperty("not_like")
    DOES_NOT_CONTAIN("does_not_contain"),

//    @JsonProperty("starts_with")
    STARTS_WITH("starts_with"),

//    @JsonProperty("ends_with")
    ENDS_WITH("ends_with"),

//    @JsonProperty("is_not_empty")
    IS_NOT_EMPTY("is_not_empty"),

//    @JsonProperty("is_empty")
    IS_EMPTY("is_empty"),

//    @JsonProperty("exists")
    EXISTS("exists"),

//    @JsonProperty("not_exists")
    NOT_EXISTS("not_exists"),

//    @JsonProperty("less_or_equal")
    LESS_THAN_OR_EQUAL("less_than_or_equal"),

//    @JsonProperty("greater_or_equal")
    GREATER_THAN_OR_EQUAL("greater_than_or_equal"),

//    @JsonProperty("is_null")
    NULL("is_null"),

//    @JsonProperty("is_not_null")
    NOT_NULL("is_not_null");

}