package ir.smartech.cro.storage.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
class GatewayConfig {
    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()
}
