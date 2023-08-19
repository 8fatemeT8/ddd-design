package ir.smartech.cro.storage.data.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.DataMapper
import ir.smartech.cro.storage.data.clickhouse.ClickhouseService
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CollectorListener(
    private val objectMapper: ObjectMapper,
    private val dataMapper: DataMapper,
    private val clickhouseService: ClickhouseService,
) {

    private val logger: Logger = LoggerFactory.getLogger(CollectorListener::class.java)

    @KafkaListener(
        topics = [KafkaTopic.gatewayEmit],
        autoStartup = "\${spring.kafka.consumer.gateway-data.activation-status}",
        concurrency = "\${spring.kafka.consumer.gateway-data.concurrency}",
        groupId = "\${spring.kafka.consumer.gateway-data.group-id}",
        properties = ["max.poll.records=100000"]
    )
    fun persistDataOnClickhouse(message: String, ack: Acknowledgment) {
        logger.info("start reading :\n $message")

        val dto = objectMapper.readValue(message, KafkaMessage::class.java)
        val mappedData = dataMapper.convertToClickhouseMessage(dto)
        logger.info("mapped to clickhouseMessage dto :\n $mappedData")
        clickhouseService.insertValue(mappedData)
        logger.info("inset data to clickhouse")

        ack.acknowledge()
        logger.info("commit $message")
    }
}