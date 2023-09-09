package ir.smartech.cro.storage.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableKafka
open class CollectorConfig {
    @Bean
    open fun objectMapper(): ObjectMapper = ObjectMapper()

    @Bean
    open fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()
}
