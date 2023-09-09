package ir.smartech.cro.storage.common

import ir.smartech.cro.storage.config.kafka.KafkaTopic
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.Future

@ExtendWith(SpringExtension::class)
@Import(TestConfig::class)
@EmbeddedKafka(
    partitions = 1,
    topics = [KafkaTopic.gatewayEmit]
)
@TestPropertySource(locations = ["classpath:application.yml"])
open class BaseTest {

    @Autowired
    lateinit var embeddedKafka: EmbeddedKafkaBroker

    fun <V> produce(
        topic: String,
        key: String?,
        value: V?,
    ): Future<RecordMetadata>? {
        val producer = embeddedKafka.produceToTopic<V>()
        val record = producer.send(
            ProducerRecord(
                topic,
                null,
                null,
                key,
                value,
            )
        )
        producer.close()

        return record
    }

    fun getSingleRecord(topic: String): ConsumerRecord<String, Any> {
        val consumer = embeddedKafka.consumeFromTopic(topic, "consumer_$topic")
        val record = KafkaTestUtils.getSingleRecord(consumer, topic)
        consumer.close()

        return record
    }

    fun EmbeddedKafkaBroker.consumeFromTopic(topic: String, group: String = "consumer"): Consumer<String, Any> {
        val configs = KafkaTestUtils.consumerProps(group, "true", this)
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java

        val consumer = DefaultKafkaConsumerFactory<String, Any>(configs).createConsumer()
        consumer.subscribe(listOf(topic))

        return consumer
    }

    fun <V> EmbeddedKafkaBroker.produceToTopic(): Producer<String, V> {
        val configs = KafkaTestUtils.producerProps(this)

        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return DefaultKafkaProducerFactory<String, V>(configs).createProducer()
    }
}