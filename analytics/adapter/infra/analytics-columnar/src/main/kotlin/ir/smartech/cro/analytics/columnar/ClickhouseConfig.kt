package ir.smartech.cro.analytics.columnar

import com.clickhouse.jdbc.ClickHouseDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.util.Properties


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
        val properties = Properties()
        properties.setProperty("password",password)
        properties.setProperty("user",username)
        return ClickHouseDataSource(dataSourceUrl, properties)
    }
}