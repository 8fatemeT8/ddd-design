package ir.smartech.cro.storage.service

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.config.kafka.KafkaPublisher
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.postgres.ReturnType
import org.springframework.stereotype.Service
import java.lang.NumberFormatException

@Service
class CollectorService(
    private val kafkaPublisher: KafkaPublisher<String, Any?>,
    private val productService: ProductService,
    private val objectMapper: ObjectMapper
) {

    fun validate(dto: HashMap<String, String>): List<String> {
        val result = arrayListOf<String>()
        val businessId = checkBasicValidation(dto, result)
        if (result.isNotEmpty()) return result
        val product = productService.getById(dto["id"]?.toInt()!!).get()
        val schema = productService.getSchema(product, businessId!!).data
        dto.forEach { (k, v) ->
            if (!arrayListOf("id", "businessId").contains(k))
                when (schema?.get(k)) {
                    ReturnType.NUMBER ->
                        try {
                            v.toInt()
                            v.toLong()
                            v.toBigDecimal()
                            v.toBigInteger()
                            v.toFloat()
                            v.toDouble()
                        } catch (e: NumberFormatException) {
                            result.add("send $k value with ${ReturnType.NUMBER} type")
                        }

                    ReturnType.BOOLEAN ->
                        try {
                            v.toBooleanStrict()
                        } catch (e: IllegalArgumentException) {
                            result.add("send $k value with ${ReturnType.BOOLEAN} type")
                        }

                    ReturnType.JSON ->
                        try {
                            objectMapper.readValue(v, HashMap::class.java)
                        } catch (e: JsonParseException) {
                            result.add("send $k value with ${ReturnType.JSON} type")
                        }

                    else -> {}
                }
        }
        return result
    }

    private fun checkBasicValidation(
        dto: HashMap<String, String>,
        result: ArrayList<String>
    ): Int? {
        if (dto["id"] == null) result.add("the id must not be null")
        val businessId = dto["businessId"]?.toInt()
        if (businessId == null) result.add("the businessId must not be null")
        return businessId
    }

    fun writeToKafka(message: HashMap<String, String>) {
        kafkaPublisher.publish(arrayListOf(message), KafkaTopic.gatewayEmit)
    }
}