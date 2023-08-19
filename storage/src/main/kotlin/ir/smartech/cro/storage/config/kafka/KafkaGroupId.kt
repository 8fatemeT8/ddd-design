package ir.smartech.cro.storage.config.kafka

import org.springframework.context.annotation.Configuration

@Configuration
class KafkaGroupId {

    companion object {
        const val readDailyGateway = "read-daily-data"
    }

}