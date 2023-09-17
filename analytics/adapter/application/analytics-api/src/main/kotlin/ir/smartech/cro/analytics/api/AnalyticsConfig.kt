package ir.smartech.cro.analytics.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class AnalyticsConfig {
    @Value("\${spring.datasource.url}")
    val dataSourceUrl: String? = null

    @Value("\${spring.datasource.password}")
    val password: String? = null

    @Value("\${spring.datasource.username}")
    val username: String? = null

    @Value("\${spring.datasource.driver-class-name}")
    val driverName: String? = null

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    fun primaryDataSource(): DataSource? {
        return DataSourceBuilder.create()
            .url(dataSourceUrl)
            .username(username)
            .password(password)
            .driverClassName(driverName).build()
    }
}
