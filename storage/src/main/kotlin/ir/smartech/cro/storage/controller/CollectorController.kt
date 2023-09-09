package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.ReturnType
import ir.smartech.cro.storage.service.CollectorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * this controller has two api:
 * 1 . receiving data
 * 2 . return all columns types
 */
@RestController
@RequestMapping("/api/web/cro/collector")
class CollectorController(
    private val collectorService: CollectorService,
) {

    /**
     * with this api we can collect the client data.
     * First it is stored in Kafka and then with clickhouse listeners, the data will be stored in clickhouse
     */
    @PostMapping("/receive")
    fun receiveData(@RequestBody message: HashMap<String?, String?>): ResponseEntity<Any> {
        val result = collectorService.validate(message)
        if (result.isNotEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(result)
        collectorService.writeToKafka(message)
        return ResponseEntity.ok().build()
    }

    /**
     * This api returns all acceptable column types
     */
    @GetMapping("/return-type")
    fun getReturnTypes(): ResponseEntity<List<String>> = ResponseEntity.ok(ReturnType.values().map { it.toString() })
}