package ir.smartech.cro.analytics.adapter.userapi

import ir.smartech.cro.analytics.adapter.compositionroot.CompositionConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(CompositionConfig::class)
@SpringBootApplication
class AnalyticsApplication

fun main(args: Array<String>) {
    runApplication<AnalyticsApplication>(*args)
}