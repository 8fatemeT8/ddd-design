package ir.smartech.cro.storage.data

import ir.smartech.cro.storage.data.clickhouse.dto.ClickhouseMessage
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import org.springframework.stereotype.Component

@Component
class DataMapper {
    fun convertToClickhouseMessage(kafkaMessage: KafkaMessage): ClickhouseMessage {
        // TODO implement map
        return ClickhouseMessage()
    }
}