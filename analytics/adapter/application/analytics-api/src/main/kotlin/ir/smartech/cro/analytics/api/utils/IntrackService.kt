package ir.smartech.cro.analytics.api.utils

import ir.smartech.cro.analytics.domain.funnel.api.dto.SegmentFunnelQueryDto
import jakarta.servlet.ServletException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class IntrackService(
    private val restTemplate: RestTemplate,
) {
    @Value("\${intrack.url}")
    var url: String? = null

    @Value("\${intrack.token}")
    var token: String? = null


    fun createSegment(dto: SegmentFunnelQueryDto, name: String, productId: String?): IdDto {
        val header = initialHeaders(productId)
        val requestDto = initialBodyDto(name, dto)
        val body = HttpEntity(requestDto, header)
        try {
            val response = restTemplate.postForEntity(url!!, body, HashMap::class.java)
            val responseDto = response.body?.get("result") as LinkedHashMap<String, Int>
            return IdDto(responseDto["id"]?.toLong())
        }catch (e:Exception){
            println(e)
        }
        return IdDto()
    }

    private fun initialHeaders(productId: String?): HttpHeaders {
        val header = HttpHeaders()
        header.set("Authorization", "Basic $token")
        header.set("Product-Id", productId)
        header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        return header
    }

    private fun initialBodyDto(name: String, dto: SegmentFunnelQueryDto) =
        IntrackSegmentCreateDto().apply {
            this.name = name
            terms[0].predicates[0].stringValue = dto.userIds.joinToString(",")
        }
}