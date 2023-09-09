package ir.smartech.cro.storage.config.kafka

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaPublisher<K, V>(private val kafkaTemplate: KafkaTemplate<K, V>) {
    fun publish(events: List<V>, topic: String) {
        for (event in events) {
            kafkaTemplate.send(topic, event)
        }
    }

    fun publish(records: List<ProducerRecord<K, V>>) {
        for (record in records) {
            kafkaTemplate.send(record)
        }
    }
}