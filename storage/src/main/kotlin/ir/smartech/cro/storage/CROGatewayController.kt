package ir.smartech.cro.storage

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web/cro/gateway")
class CROGatewayController(private val croService: CROService) {
}