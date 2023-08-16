package ir.smartech.cro.storage

import ir.smartech.cro.storage.config.kafka.KafkaPublisher
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import org.springframework.stereotype.Service

@Service
class Service(private val kafkaPublisher: KafkaPublisher<String, Any?>) {

    fun writeToKafka(message: KafkaMessage) {
        kafkaPublisher.publish(arrayListOf(message), KafkaTopic.gatewayEmit)
    }
}