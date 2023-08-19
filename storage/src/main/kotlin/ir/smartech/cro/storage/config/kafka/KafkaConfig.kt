package ir.smartech.cro.storage.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.*
import kotlin.collections.HashMap


@Configuration
class KafkaConfig {

    @Value("\${spring.kafka.server.address}")
    var serverAddress: String? = null

    @Bean
    fun producerFactory(): ProducerFactory<Any, Any> {

        val config: MutableMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = serverAddress!!
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<*, *>? {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun kafkaConsumerProps(): Properties {
        val properties = Properties()
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress)
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        return properties
    }

    @Bean
    fun dailyKafkaConsumer(): KafkaConsumer<*, *> {
        val props = kafkaConsumerProps()
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaGroupId.readDailyGateway)
        return KafkaConsumer<String, Any>(props)
    }
}