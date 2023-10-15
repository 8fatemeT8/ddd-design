package ir.smartech.cro.analytics.api.funnel

import BaseMockMVCTest
import com.clickhouse.jdbc.ClickHouseDataSource
import ir.smartech.cro.analytics.api.dto.ClientCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.ConditionCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.FunnelQueryRequest
import ir.smartech.cro.analytics.api.dto.funnel.StepCreateDto
import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.rdb.repository.JpaClientRepository
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FunnelQueryTest(
    @Autowired
    private val repo: JpaFunnelRepository,
    @Autowired
    private val clientRepository: JpaClientRepository,
    @Autowired
    private val dataSource: ClickHouseDataSource
) : BaseMockMVCTest() {

    @BeforeEach
    fun initScript() {
        val statement = dataSource.connection.createStatement()
        statement.executeQuery("DROP DATABASE IF EXISTS analytics")
        statement.executeQuery("CREATE DATABASE IF NOT EXISTS analytics ENGINE = Memory;")
        statement.executeQuery(
            "create table analytics.intrack_events" +
                    "(" +
                    "    product_id String," +
                    "    user_id    String," +
                    "    timestamp  UInt64," +
                    "    event_name String," +
                    "    boolean1   Nullable(Bool)," +
                    "    boolean2   Nullable(Bool)," +
                    "    boolean3   Nullable(Bool)," +
                    "    boolean4   Nullable(Bool)," +
                    "    boolean5   Nullable(Bool)," +
                    "    boolean6   Nullable(Bool)," +
                    "    boolean7   Nullable(Bool)," +
                    "    boolean8   Nullable(Bool)," +
                    "    boolean9   Nullable(Bool)," +
                    "    date1      Nullable(UInt64)," +
                    "    date2      Nullable(UInt64)," +
                    "    date3      Nullable(UInt64)," +
                    "    date4      Nullable(UInt64)," +
                    "    date5      Nullable(UInt64)," +
                    "    date6      Nullable(UInt64)," +
                    "    date7      Nullable(UInt64)," +
                    "    date8      Nullable(UInt64)," +
                    "    date9      Nullable(UInt64)," +
                    "    numeric1   Nullable( Decimal (38, 18))," +
                    "    numeric2   Nullable( Decimal (38, 18))," +
                    "    numeric3   Nullable( Decimal (38, 18))," +
                    "    numeric4   Nullable( Decimal (38, 18))," +
                    "    numeric5   Nullable( Decimal (38, 18))," +
                    "    numeric6   Nullable( Decimal (38, 18))," +
                    "    numeric7   Nullable( Decimal (38, 18))," +
                    "    numeric8   Nullable( Decimal (38, 18))," +
                    "    numeric9   Nullable( Decimal (38, 18))," +
                    "    string1    Nullable(String)," +
                    "    string2    Nullable(String)," +
                    "    string3    Nullable(String)," +
                    "    string4    Nullable(String)," +
                    "    string5    Nullable(String)," +
                    "    string6    Nullable(String)," +
                    "    string7    Nullable(String)," +
                    "    string8    Nullable(String)," +
                    "    string9    Nullable(String)" +
                    ") engine = MergeTree " +
                    "PARTITION BY product_id" +
                    "        PRIMARY KEY (user_id, product_id, timestamp)" +
                    "        ORDER BY (user_id, product_id, timestamp)" +
                    "        SETTINGS index_granularity = 8192;"
        )
        statement.executeQuery(
            "INSERT INTO analytics.intrack_events (product_id, user_id, timestamp, event_name, string1, string2, numeric1)" +
                    "VALUES (2, '111', 1111, 'event1', 'IOS', 'hello', 1)," +
                    "       (2, '111', 11114, 'event2', 'IOS', 'hello', 2)," +
                    "       (2, '111', 12455, 'event3', 'IOS', 'hello', 3)," +
                    "       (2, '111', 11134, 'event2', 'IOS', 'hello', 4)," +
                    "       (2, '111', 3342228, 'event4', 'Android', 'hi', 5)," +
                    "       (2, '111', 3242228, 'event3', 'Android', 'hello', 6)," +
                    "       (2, '111', 3242229, 'event1', 'Windows', 'hello', 7)," +
                    "       (2, '111', 11111, 'event4', 'IOS', 'hello', 8)," +
                    "       (2, '111', 3242225, 'event2', 'Windows', 'hi', 9)," +
                    "       (2, '111', 3242223, 'event1', 'Windows', 'hi', 1)," +
                    "       (2, '222', 433398, 'event3', 'IOS', 'hello', 4)," +
                    "       (2, '222', 1115, 'event1', 'IOS', 'hi', 8)," +
                    "       (2, '222', 11122, 'event2', 'IOS', 'hi', 2)," +
                    "       (2, '222', 1112, 'event1', 'IOS', 'hi', 3)," +
                    "       (2, '222', 1113, 'event2', 'IOS', 'hi', 9)," +
                    "       (2, '222', 11350000, 'event4', 'Android', 'hello', 5)," +
                    "       (2, '333', 111565, 'event2', 'Android', 'hello', 6)," +
                    "       (2, '333', 1111121, 'event3', 'Android', 'hi', 10)," +
                    "       (2, '333', 11353, 'event1', 'Windows', 'hello', 3)," +
                    "       (2, '333', 112998, 'event4', 'Windows', 'hello', 1)," +
                    "       (2, '333', 51111, 'event1', 'Android', 'hello', 7)," +
                    "       (2, '333', 12128, 'event2', 'Windows', 'hi', 4)," +
                    "       (2, '333', 112534, 'event3', 'Windows', 'hello', 3);"
        )
        statement.close()
    }

    @BeforeEach
    fun setup() {
        val dto = ClientCreateDto().apply {
            name = "Intrack"
            description = "test test"
            enabled = true
        }

        mockMvc.sendPost("/api/web/analytics/client", dto)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val client = clientRepository.findAll().first()

        val funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "first cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "event1"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "string1"
                            eventPropertyType = PropertyType.TEXT
                            value = "IOs,Android"
                            negate = false
                            operator = Operator.ONE_OF
                        }
                    )
                },
                StepCreateDto().apply {
                    eventName = "event2"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "string2"
                            eventPropertyType = PropertyType.TEXT
                            value = "hello"
                            negate = false
                            operator = Operator.EQUAL
                        }
                    )
                },

                StepCreateDto().apply {
                    eventName = "event3"
                    stepNumber = 3
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "numeric1"
                            eventPropertyType = PropertyType.NUMBER
                            value = "4"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }

        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assert( repo.findAll().isNotEmpty())
    }

    @AfterEach
    fun cleanup() {
        repo.deleteAll()
        clientRepository.deleteAll()
    }

    @Test
    fun `funnel simple query`() {
        val dto = FunnelQueryRequest().apply {
            completionTime = 1000000
        }

        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${repo.findAll().first().id}/query",
            dto,
            clientRepository.findAll().first().id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        assert(result == """[{"stepNumber":1,"count":1,"splitValue":null},{"stepNumber":2,"count":1,"splitValue":null},{"stepNumber":3,"count":0,"splitValue":null}]""")
    }

    @Test
    fun `funnel splitBy query`() {
        val dto = FunnelQueryRequest().apply {
            completionTime = 1000000
            splitBy = "string1"
        }

        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${repo.findAll().first().id}/query-split",
            dto,
            clientRepository.findAll().first().id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        assert(result == """[{"stepNumber":1,"count":0,"splitValue":"IOS"},{"stepNumber":1,"count":0,"splitValue":"Windows"},{"stepNumber":1,"count":1,"splitValue":"Android"},{"stepNumber":2,"count":0,"splitValue":"IOS"},{"stepNumber":2,"count":0,"splitValue":"Windows"},{"stepNumber":2,"count":1,"splitValue":"Android"},{"stepNumber":3,"count":0,"splitValue":"IOS"},{"stepNumber":3,"count":0,"splitValue":"Windows"},{"stepNumber":3,"count":0,"splitValue":"Android"}]""")
    }

    @Test
    fun `funnel segment query`() {
        val dto = FunnelQueryRequest().apply {
            completionTime = 1000000
            stepNumbers = 2
        }

        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${repo.findAll().first().id}/query-segment",
            dto,
            clientRepository.findAll().first().id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        assert(result == """{"id":null}""")
    }
}