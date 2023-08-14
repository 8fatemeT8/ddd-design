package ir.smartech.cro.storage

import ir.smartech.cro.storage.config.kafka.KafkaMessage
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/gateway")
class Controller(private val service: Service) {

    @PostMapping("/write")
    fun receiveData(@RequestBody message: KafkaMessage) {
        // TODO : add validation, it must save valid data to kafka
        service.writeToKafka(message)
    }
}