package ir.smartech.cro.storage

import ir.smartech.cro.storage.data.clickhouse.ClickhouseService
import ir.smartech.cro.storage.data.kafka.dto.KafkaMessage
import ir.smartech.cro.storage.data.parquet.ParquetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/collector")
class Controller(
    private val service: Service,
    private val clickhouseService: ClickhouseService,
    private val parquetService: ParquetService
) {

    @PostMapping("/receive")
    fun receiveData(@RequestBody message: KafkaMessage) {
        // TODO : add validation, it must save valid data to kafka
        service.writeToKafka(message)
    }

    @PostMapping("/table")
    fun createTable() {
        clickhouseService.createTable()
    }

    @GetMapping
    fun readClickhouse(): ResponseEntity<*> {
        return ResponseEntity.ok(clickhouseService.readData())
    }
}