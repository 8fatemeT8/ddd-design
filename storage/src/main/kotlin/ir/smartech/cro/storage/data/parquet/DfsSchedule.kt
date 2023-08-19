package ir.smartech.cro.storage.data.parquet

import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.PartitionInfo
import org.apache.kafka.common.TopicPartition
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Duration


@Component
class DfsSchedule(
    private val parquetService: ParquetService,
    private val kafkaConsumer: KafkaConsumer<String, Any>,
    private val objectMapper: ObjectMapper,
) {

    @Scheduled(cron = "59 59 23 * * *")
    fun saveAsParquet() {
        val partitionInfos: List<PartitionInfo> = kafkaConsumer.partitionsFor(KafkaTopic.gatewayEmit)
        val topicPartitionList = partitionInfos.map { TopicPartition(KafkaTopic.gatewayEmit, it.partition()) }
        kafkaConsumer.assign(topicPartitionList)

        val list = kafkaConsumer.poll(Duration.ofMillis(100))
        if (list.isEmpty) return

        parquetService.writeData(list.map { objectMapper.readValue(it.value().toString(), KafkaMessage::class.java) })
        kafkaConsumer.committed(topicPartitionList.toSet())
    }
}