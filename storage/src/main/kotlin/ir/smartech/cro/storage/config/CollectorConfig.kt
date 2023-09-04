package ir.smartech.cro.storage.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CollectorConfig {
    @Bean
    open fun objectMapper(): ObjectMapper = ObjectMapper()
}
