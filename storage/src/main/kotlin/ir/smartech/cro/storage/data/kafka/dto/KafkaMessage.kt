package ir.smartech.cro.storage.data.kafka.dto

import java.io.Serializable

// TODO add correct fields and structure
data class KafkaMessage(var message: String? = null, var name: String? = null, var age: Int? = null) : Serializable