package ir.smartech.cro.storage.data.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.config.kafka.KafkaMessage
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.DataMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class GatewayListener(private val objectMapper: ObjectMapper, private val dataMapper: DataMapper) {

    private val logger: Logger = LoggerFactory.getLogger(GatewayListener::class.java)

    @KafkaListener(
        topics = [KafkaTopic.gatewayTest],
        autoStartup = "\${spring.kafka.consumer.gateway-data.activation-status}",
        concurrency = "\${spring.kafka.consumer.gateway-data.concurrency}",
        groupId = "\${spring.kafka.consumer.gateway-data.group-id}"
    )
    fun persistDataOnClickhouse(message: String, ack: Acknowledgment) {
        logger.info("start reading :\n $message")

        val dto = objectMapper.readValue(message, KafkaMessage::class.java)
        // TODO use dataMapper and map to clickhouse entity

        ack.acknowledge()
        logger.info("commit $message")
    }
}