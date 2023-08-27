package ir.smartech.cro.storage.controller

import ir.smartech.cro.storage.data.postgres.ReturnType
import ir.smartech.cro.storage.service.CollectorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web/cro/collector")
class CollectorController(
    private val collectorService: CollectorService,
) {

    @PostMapping("/receive")
    fun receiveData(@RequestBody message: HashMap<String?, String?>): ResponseEntity<Any> {
        val result = collectorService.validate(message)
        if (result.isNotEmpty())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(result)
        collectorService.writeToKafka(message)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/return-type")
    fun getReturnTypes(): ResponseEntity<List<String>> = ResponseEntity.ok(ReturnType.values().map { it.toString() })
}