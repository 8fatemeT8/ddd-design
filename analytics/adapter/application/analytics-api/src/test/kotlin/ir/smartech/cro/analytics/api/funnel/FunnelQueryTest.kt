import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestConstructor
import org.testcontainers.clickhouse.ClickHouseContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Testcontainers
class FunnelQueryTest : BaseMockMVCTest() {

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

        @JvmStatic
        @DynamicPropertySource
        fun overrideProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") {
                val url = "jdbc:postgresql://${postgresContainer.host}:${postgresContainer.getMappedPort(5432)}/sample_db"
                println("######## OBTAINED DATASOURCE URL IS: $url")
                return@add url
            }

            registry.add("spring.secondDatasource.url") {
                val url = "jdbc:clickhouse://${clickHouseContainer.host}:${clickHouseContainer.getMappedPort(8123)}/sample_db"
                println("######## OBTAINED DATASOURCE URL IS: $url")
                return@add url
            }
        }
    }

    @Test
    fun contextLoads() {
    }
}