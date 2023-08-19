package ir.smartech.cro.storage.config

import cc.blynk.clickhouse.ClickHouseDataSource
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
class CollectorConfig {

    @Value("\${spring.datasource.url}")
    val dataSourceUrl: String? = null

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()

    @Bean
    fun datasource(): ClickHouseDataSource {
        val datasource= ClickHouseDataSource(dataSourceUrl)
        datasource.connection.createStatement().execute("CREATE DATABASE IF NOT EXISTS CRO")
        return datasource
    }
}
