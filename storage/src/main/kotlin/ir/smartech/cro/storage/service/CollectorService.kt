package ir.smartech.cro.storage.service

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.config.kafka.KafkaPublisher
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.postgres.ReturnType
import ir.smartech.cro.storage.data.postgres.repository.ClientSchemaRepository
import ir.smartech.cro.storage.data.postgres.repository.ClientRepository
import org.springframework.stereotype.Service
import java.lang.NumberFormatException
import java.util.*

/**
 * this class use for implementing Collector logic (for instance: validate data or publish data to kafka)
 */
@Service
class CollectorService(
    private val kafkaPublisher: KafkaPublisher<String, Any?>,
    private val clientRepository: ClientRepository,
    private val clientSchemaRepository: ClientSchemaRepository,
    private val objectMapper: ObjectMapper
) {

    /**
     * this method validates input based on currentClient's schema
     * just validates required fields and typeMisMatchException
     */
    fun validate(dto: HashMap<String?, String?>): List<String> {
        val result = arrayListOf<String>()
        checkRequiredValidation(dto, result)
        if (result.isNotEmpty()) return result
        /* TODO get from spring context*/
        val clientId = clientRepository.findAll().first().id
        val schema = clientSchemaRepository.findByClientId(clientId!!).data
        dto.forEach { (k, v) ->
            if (!arrayListOf("id", "productId").contains(k))
                when (schema?.get(k)) {
                    ReturnType.NUMBER ->
                        try {
                            v?.toInt()
                            v?.toLong()
                            v?.toBigDecimal()
                            v?.toBigInteger()
                            v?.toFloat()
                            v?.toDouble()
                        } catch (e: NumberFormatException) {
                            result.add("send $k value with ${ReturnType.NUMBER} type")
                        }

                    ReturnType.BOOLEAN ->
                        try {
                            v?.toBooleanStrict()
                        } catch (e: IllegalArgumentException) {
                            result.add("send $k value with ${ReturnType.BOOLEAN} type")
                        }

                    ReturnType.JSON ->
                        try {
                            if (v != null)
                                objectMapper.readValue(v, HashMap::class.java)
                        } catch (e: JsonParseException) {
                            result.add("send $k value with ${ReturnType.JSON} type")
                        }

                    else -> {}
                }
        }
        return result
    }

    private fun checkRequiredValidation(
        dto: HashMap<String?, String?>, result: ArrayList<String>
    ): String? {
        val businessId = dto["productId"]
        if (businessId == null) result.add("the productId must not be null")
        return businessId
    }

    /**
     * write message to currentClient's kafkaTopic
     */
    fun writeToKafka(message: HashMap<String?, String?>) {
        // TODO get topic name from security context
        kafkaPublisher.publish(arrayListOf(message), KafkaTopic.COLLECTOR_EMIT)
    }
}