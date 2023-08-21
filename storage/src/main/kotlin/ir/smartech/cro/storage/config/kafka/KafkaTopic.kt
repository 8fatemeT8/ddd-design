package ir.smartech.cro.storage.config.kafka

import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopic {

    companion object {
        const val gatewayEmit: String = "gateway_emit"
    }
}