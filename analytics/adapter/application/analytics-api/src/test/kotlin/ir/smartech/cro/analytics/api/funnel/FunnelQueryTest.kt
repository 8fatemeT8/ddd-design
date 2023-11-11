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
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.api.dto.FunnelQueryResponse
import ir.smartech.cro.analytics.rdb.repository.JpaClientRepository
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

class FunnelQueryTest(
    @Autowired
    private val repo: JpaFunnelRepository,
    @Autowired
    private val funnelService: FunnelService,
    @Autowired
    private val clientRepository: JpaClientRepository,
    @Autowired
    private val dataSource: ClickHouseDataSource
) : BaseMockMVCTest() {
    @BeforeEach
    fun initScript() {
        val statement = dataSource.connection.createStatement()
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

        var funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "first cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "event1"
                    stepNumber = 1
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event2"
                    stepNumber = 2
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event3"
                    stepNumber = 3
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event4"
                    stepNumber = 4
                    stepConditions = arrayListOf()
                }
            )
        }
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        funnelDto = FunnelCreateDto().apply {
            productNumber = 3
            name = "second cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "event1"
                    stepNumber = 1
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event2"
                    stepNumber = 2
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event3"
                    stepNumber = 3
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event4"
                    stepNumber = 4
                    stepConditions = arrayListOf()
                }
            )
        }
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()


        funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "third cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "event1"
                    stepNumber = 1
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event2"
                    stepNumber = 2
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event3"
                    stepNumber = 3
                    stepConditions = arrayListOf()
                },
                StepCreateDto().apply {
                    eventName = "event4"
                    stepNumber = 4
                    stepConditions = arrayListOf(
                        ConditionCreateDto(
                            operator = Operator.EQUAL,
                            value = "android",
                            negate = false,
                            eventProperty = "string1",
                            eventPropertyType = PropertyType.TEXT
                        ),
                        ConditionCreateDto(
                            operator = Operator.GREATER_THAN_OR_EQUAL,
                            value = "100000",
                            negate = false,
                            eventProperty = "numeric1",
                            eventPropertyType = PropertyType.NUMBER
                        )
                    )
                }
            )
        }
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assert(repo.findAll().isNotEmpty())
    }

    @AfterEach
    fun cleanup() {
        repo.deleteAll()
        clientRepository.deleteAll()
        val statement = dataSource.connection.createStatement()
        statement.executeQuery("DROP DATABASE IF EXISTS analytics")
        statement.close()
    }

    @Test
    fun `funnel simple query`() {
        val funnels = repo.findAll()

        // simple funnel query
        insertDataOnDb(FunnelDataSetQuery.firstDataSet())
        val funnel = funnels.find { it.name == "first cro funnel" }
        val dto = FunnelQueryRequest().apply {
            completionTime = null
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }

        mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isNotAcceptable)

        dto.apply { completionTime = 86400 }
        var result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        var expectedResult = arrayListOf(
            FunnelQueryResponse().apply {
                stepNumber = 1
                count = 7
            }, FunnelQueryResponse().apply {
                stepNumber = 2
                count = 7
            },
            FunnelQueryResponse().apply {
                stepNumber = 3
                count = 6
            }, FunnelQueryResponse().apply {
                stepNumber = 4
                count = 4
            })
        assert(result == objectMapper.writeValueAsString(expectedResult))

        // simple funnel query with 3 hour completionTime
        dto.apply { completionTime = 10800 }
        result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        expectedResult = arrayListOf(
            FunnelQueryResponse().apply {
                stepNumber = 1
                count = 7
            }, FunnelQueryResponse().apply {
                stepNumber = 2
                count = 6
            },
            FunnelQueryResponse().apply {
                stepNumber = 3
                count = 5
            }, FunnelQueryResponse().apply {
                stepNumber = 4
                count = 3
            })
        assert(result == objectMapper.writeValueAsString(expectedResult))
    }

    @Test
    fun `test funnel productNumber condition`() {
        val funnels = repo.findAll()

        // type 2 with condition on productNumber
        insertDataOnDb(FunnelDataSetQuery.secondDataSet())
        var funnel = funnels.find { it.name == "second cro funnel" }
        var dto = FunnelQueryRequest().apply {
            completionTime = 86400
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }
        var result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val expectedResult = arrayListOf(
            FunnelQueryResponse().apply {
                stepNumber = 1
                count = 1
            }, FunnelQueryResponse().apply {
                stepNumber = 2
                count = 1
            },
            FunnelQueryResponse().apply {
                stepNumber = 3
                count = 1
            }, FunnelQueryResponse().apply {
                stepNumber = 4
                count = 0
            })
        assert(result == objectMapper.writeValueAsString(expectedResult))
    }

    @Test
    fun `test funnel productNumber condition 2`() {
        val funnels = repo.findAll()

        // type 2 with condition on productNumber
        insertDataOnDb(FunnelDataSetQuery.secondDataSet())
        val funnel = funnels.find { it.name == "first cro funnel" }
        val dto = FunnelQueryRequest().apply {
            completionTime = 86400
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }
        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val expectedResult = arrayListOf(
            FunnelQueryResponse().apply {
                stepNumber = 1
                count = 6
            }, FunnelQueryResponse().apply {
                stepNumber = 2
                count = 6
            },
            FunnelQueryResponse().apply {
                stepNumber = 3
                count = 5
            }, FunnelQueryResponse().apply {
                stepNumber = 4
                count = 4
            })
        assert(result == objectMapper.writeValueAsString(expectedResult))
    }

    @Test
    fun `test funnel with stepCondition`() {
        insertDataOnDb(FunnelDataSetQuery.firstDataSet())
        val funnel = repo.findAll().find { it.name == "third cro funnel" }
        val dto = FunnelQueryRequest().apply {
            completionTime = 86400
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }
        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val expectedResult = arrayListOf(
            FunnelQueryResponse().apply {
                stepNumber = 1
                count = 7
            }, FunnelQueryResponse().apply {
                stepNumber = 2
                count = 7
            },
            FunnelQueryResponse().apply {
                stepNumber = 3
                count = 6
            }, FunnelQueryResponse().apply {
                stepNumber = 4
                count = 1
            })
        assert(result == objectMapper.writeValueAsString(expectedResult))
    }

    @Test
    // TODO there is some problem on split result
    fun `funnel splitBy query`() {
        insertDataOnDb(FunnelDataSetQuery.firstDataSet())
        val funnel = repo.findAll().find { it.name == "first cro funnel" }
        val dto = FunnelQueryRequest().apply {
            completionTime = 86400
            splitBy = "string1"
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }
        val result = mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query-split",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val expectedResult = funnelSplitQueryResponse()
        assert(result == objectMapper.writeValueAsString(expectedResult))
    }

    private fun funnelSplitQueryResponse() = arrayListOf(
        FunnelQueryResponse().apply {
                stepNumber = 1
                count = 1
                splitValue = "windows"
            },
        FunnelQueryResponse().apply {
                stepNumber = 1
                count = 2
                splitValue = "android"
            },
        FunnelQueryResponse().apply {
                stepNumber = 1
                count = 2
                splitValue = "iOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 1
                count = 1
                splitValue = "linux"
            },
        FunnelQueryResponse().apply {
                stepNumber = 1
                count = 1
                splitValue = "macOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 2
                count = 1
                splitValue = "windows"
            },
        FunnelQueryResponse().apply {
                stepNumber = 2
                count = 0
                splitValue = "android"
            },
        FunnelQueryResponse().apply {
                stepNumber = 2
                count = 1
                splitValue = "iOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 2
                count = 1
                splitValue = "linux"
            },
        FunnelQueryResponse().apply {
                stepNumber = 2
                count = 1
                splitValue = "macOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 3
                count = 1
                splitValue = "windows"
            },
        FunnelQueryResponse().apply {
                stepNumber = 3
                count = 0
                splitValue = "android"
            },
        FunnelQueryResponse().apply {
                stepNumber = 3
                count = 1
                splitValue = "iOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 3
                count = 0
                splitValue = "linux"
            },
        FunnelQueryResponse().apply {
                stepNumber = 3
                count = 1
                splitValue = "macOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 4
                count = 1
                splitValue = "windows"
            },
        FunnelQueryResponse().apply {
                stepNumber = 4
                count = 0
                splitValue = "android"
            },
        FunnelQueryResponse().apply {
                stepNumber = 4
                count = 1
                splitValue = "iOS"
            },
        FunnelQueryResponse().apply {
                stepNumber = 4
                count = 0
                splitValue = "linux"
            },
        FunnelQueryResponse().apply {
                stepNumber = 4
                count = 1
                splitValue = "macOS"
            })

    @Test
    fun `funnel segment query`() {
        insertDataOnDb(FunnelDataSetQuery.firstDataSet())
        val funnel = repo.findAll().find { it.name == "third cro funnel" }

        val dto = FunnelQueryRequest().apply {
            completionTime = null
            stepNumbers = null
            startDate = Date(1675110600)
            endDate = Date(1675629000)
        }
        mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query-segment",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isNotAcceptable)

        dto.apply {
            completionTime = 86400
            stepNumbers = 1
        }
        mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query-segment",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isNotFound)

        dto.apply { stepNumbers = 3 }
        mockMvc.sendPost(
            "/api/web/analytics/funnel/${funnel?.id}/query-segment",
            dto,
            funnel?.client?.id
        ).andExpect(MockMvcResultMatchers.status().isOk)


        assert(
            arrayListOf("1", "4", "5", "6", "7").containsAll(
                funnelService.getFunnelQuerySegment(
                    funnel?.id!!,
                    funnel.client?.id!!,
                    dto.completionTime!!,
                    dto.stepNumbers,
                    dto.startDate,
                    dto.endDate
                ).userIds
            )
        )
    }

    private fun insertDataOnDb(values: String) {
        val statement = dataSource.connection.createStatement()
        statement.executeQuery(FunnelDataSetQuery.insertData(values))
        statement.close()
    }
}