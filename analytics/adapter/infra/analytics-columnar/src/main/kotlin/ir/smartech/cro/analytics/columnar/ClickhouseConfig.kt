package ir.smartech.cro.analytics.columnar

import cc.blynk.clickhouse.ClickHouseDataSource
import cc.blynk.clickhouse.settings.ClickHouseProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@ComponentScan(basePackageClasses = [ClickhouseConfig::class])
@Configuration
class ClickhouseConfig {

    @Value("\${spring.secondDatasource.url}")
    val dataSourceUrl: String? = null

    @Value("\${spring.secondDatasource.password}")
    val password: String? = null

    @Value("\${spring.secondDatasource.username}")
    val username: String? = null

    @Bean
    fun datasource(): ClickHouseDataSource {
        val properties = ClickHouseProperties()
        properties.password = password
        properties.user = username
        return ClickHouseDataSource(dataSourceUrl, properties)
    }
}