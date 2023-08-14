package ir.smartech.cro.storage

import ir.smartech.cro.storage.config.kafka.KafkaMessage
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/gateway")
class GatewayController(private val gatewayService: GatewayService) {

    @PostMapping("/write")
    fun writeDataToKafka(@RequestBody message: KafkaMessage) {
        gatewayService.test(message)
    }
}