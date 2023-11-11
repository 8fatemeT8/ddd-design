package ir.smartech.cro.analytics.api.config

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.TestConstructor
import org.testcontainers.clickhouse.ClickHouseContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Testcontainers
open class TestContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        @JvmStatic
        @Container
        val clickHouseContainer = ClickHouseContainer(DockerImageName.parse("clickhouse/clickhouse-server:23.3.8.21"))
            .withDatabaseName("sample_db")
            .withUsername("sample_user")
            .withPassword("sample_password")


        @JvmStatic
        @Container
        val postgresContainer = PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
            .withDatabaseName("sample_db")
            .withUsername("sample_user").withPassword("sample_password")

    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        postgresContainer.start()
        clickHouseContainer.start()
        TestPropertyValues.of(
            "spring.datasource.url=jdbc:postgresql://${postgresContainer.host}:${postgresContainer.getMappedPort(5432)}/sample_db",
            "spring.datasource.username=${postgresContainer.username}",
            "spring.datasource.password=${postgresContainer.password}",
            "spring.secondDatasource.url=jdbc:clickhouse://${clickHouseContainer.host}:${
                clickHouseContainer.getMappedPort(
                    8123
                )
            }/sample_db",
            "spring.secondDatasource.username=${clickHouseContainer.username}",
            "spring.secondDatasource.password=${clickHouseContainer.password}"
        ).applyTo(applicationContext.environment)
    }
}