package ir.smartech.cro.storage.config.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import kotlin.collections.HashMap


@Configuration
open class KafkaConfig {

    @Value("\${spring.kafka.producer.bootstrap-servers}")
    var serverAddress: String? = null

    @Bean
    open fun producerFactory(): ProducerFactory<Any, Any> {
        val config: MutableMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = serverAddress!!
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    open fun kafkaTemplate(): KafkaTemplate<*, *>? {
        return KafkaTemplate(producerFactory())
    }
}