package ir.smartech.cro.storage.config.kafka

import org.springframework.context.annotation.Configuration
import java.io.Serializable

@Configuration
class KafkaTopic {

    companion object {
        const val gatewayEmit: String = "gateway_emit"
        const val gatewayTest: String = "test_kafka_message"
    }
}

data class KafkaMessage(var message: String? = null, var name: String? = null, var id: Int? = null) : Serializable