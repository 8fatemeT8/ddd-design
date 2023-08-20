package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.service.CollectorService
import ir.smartech.cro.storage.data.clickhouse.ClickhouseService
import ir.smartech.cro.storage.data.parquet.ParquetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/collector")
class CollectorController(
    private val collectorService: CollectorService,
    private val clickhouseService: ClickhouseService,
    private val parquetService: ParquetService
) {

    @PostMapping("/receive")
    fun receiveData(@RequestBody message: HashMap<String, String>): ResponseEntity<Any> {
        val result = collectorService.validate(message)
        if (result.isNotEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(result)
        collectorService.writeToKafka(message)
        return ResponseEntity.ok().build()
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