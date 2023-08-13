package ir.smartech.cro.analytics.domain.funnel.api.enums

enum class EventType(val event: String) {
    Identify("identify"),
    Group("group"),
    Track("track"),
    Page("page"),
    Screen("screen"),
    Alias("alias"),
    Batch("batch");
}