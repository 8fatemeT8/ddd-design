package ir.smartech.cro.analytics.api.funnel

import BaseMockMVCTest
import ir.smartech.cro.analytics.api.dto.ClientCreateDto
import ir.smartech.cro.analytics.api.dto.funnel.*
import ir.smartech.cro.analytics.domain.common.api.enums.Operator
import ir.smartech.cro.analytics.domain.common.api.enums.PropertyType
import ir.smartech.cro.analytics.rdb.entity.JpaClient
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.rdb.repository.JpaClientRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FunnelTest(
    @Autowired
    private val repo: JpaFunnelRepository,
    @Autowired
    private val clientRepository: JpaClientRepository
) : BaseMockMVCTest() {

    @BeforeEach
    fun setup() {
        repo.deleteAll()
        clientRepository.deleteAll()
    }

    @AfterEach
    fun cleanup() {
    }

    private fun initialClient(inputName: String? = "Intrack"): JpaClient? {
        val dto = ClientCreateDto().apply {
            name = inputName
            description = "test test"
            enabled = true
        }

        mockMvc.sendPost("/api/web/analytics/client", dto)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        return clientRepository.findAll().find { it.name == dto.name }
    }

    @Test
    fun `create funnel`() {
        val client = initialClient()

        val funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "first cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "login"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "122223"
                            negate = true
                            operator = Operator.EQUAL
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "12223,4"
                            negate = true
                            operator = Operator.NOT_BETWEEN
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "1223"
                            negate = true
                            operator = Operator.ENDS_WITH
                        }
                    )
                },
                StepCreateDto().apply {
                    eventName = "logout"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "123"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }

        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("first cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andReturn()

        val result = repo.findAll().toList()
        assert(result.size == 1)
    }

    @Test
    fun `create and update`() {
        val client = initialClient()

        val funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "second cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "login"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "122223"
                            negate = true
                            operator = Operator.EQUAL
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "12223,4"
                            negate = true
                            operator = Operator.NOT_BETWEEN
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "1223"
                            negate = true
                            operator = Operator.ENDS_WITH
                        }
                    )
                },
                StepCreateDto().apply {
                    eventName = "logout"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "123"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }

        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("second cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andReturn()

        var result = repo.findAll().toList()
        assert(result.size == 1)


        val funnelUpdateDto = FunnelEditDto().apply {
            id = result[0].id
            productNumber = 6
            name = "update ${result[0].name}"
            steps = arrayListOf(
                StepViewDto().apply {
                    eventName = "update login"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionViewDto().apply {
                            eventProperty = "hhhwww"
                            eventPropertyType = PropertyType.NUMBER
                            value = "1222234444"
                            negate = true
                            operator = Operator.EQUAL
                        },
                        ConditionViewDto().apply {
                            eventProperty = "hhhttt"
                            eventPropertyType = PropertyType.NUMBER
                            value = "12223,4666"
                            negate = true
                            operator = Operator.NOT_BETWEEN
                        },
                        ConditionViewDto().apply {
                            eventProperty = "hhhuuuu"
                            eventPropertyType = PropertyType.TEXT
                            value = "1223888"
                            negate = true
                            operator = Operator.ENDS_WITH
                        }
                    )
                },
                StepViewDto().apply {
                    eventName = "updated logout"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionViewDto().apply {
                            eventProperty = "hhhccc"
                            eventPropertyType = PropertyType.TEXT
                            value = "12345"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }

        mockMvc.sendPut("/api/web/analytics/funnel", funnelUpdateDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("update ${result[0].name}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(6))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(result[0].id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("update login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("updated logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[0].eventProperty").value("hhhwww"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[0].value").value("1222234444"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[1].eventProperty").value("hhhttt"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[1].value").value("12223,4666"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[2].eventProperty").value("hhhuuuu"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions[2].value").value("1223888"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions[0].eventProperty").value("hhhccc"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions[0].value").value("12345"))
            .andReturn()

        result = repo.findAll().toList()
        assert(result.size == 1)
    }

    @Test
    fun `create and get one funnel`() {
        val client = initialClient()

        val funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "second cro funnel"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "login"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "122223"
                            negate = true
                            operator = Operator.EQUAL
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "12223,4"
                            negate = true
                            operator = Operator.NOT_BETWEEN
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "1223"
                            negate = true
                            operator = Operator.ENDS_WITH
                        }
                    )
                },
                StepCreateDto().apply {
                    eventName = "logout"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "123"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }


        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("second cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andReturn()

        val result = repo.findAll().toList()
        assert(result.size == 1)

        val get = mockMvc.sendGet("/api/web/analytics/funnel/${result.first().id}", client.id)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("second cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andReturn()

        val entity = objectMapper.readValue(get.response.contentAsString, FunnelViewDto::class.java)
        entity.steps?.sortedBy { it.stepNumber }
        assert(entity.steps?.get(0)?.eventName == "login")
        assert(entity.steps?.get(1)?.eventName == "logout")
        assert(entity.steps?.get(0)?.stepNumber == 1)
        assert(entity.steps?.get(1)?.stepNumber == 2)
        assert(entity.steps?.get(0)?.stepConditions?.size == 3)
        assert(entity.steps?.get(1)?.stepConditions?.size == 1)

    }

    @Test
    fun `create and getAll and getAllByContains funnel`() {
        var client = initialClient()

        val funnelDto = FunnelCreateDto().apply {
            productNumber = 2
            name = "funnel 1"
            steps = arrayListOf(
                StepCreateDto().apply {
                    eventName = "login"
                    stepNumber = 1
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "122223"
                            negate = true
                            operator = Operator.EQUAL
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.NUMBER
                            value = "12223,4"
                            negate = true
                            operator = Operator.NOT_BETWEEN
                        },
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "1223"
                            negate = true
                            operator = Operator.ENDS_WITH
                        }
                    )
                },
                StepCreateDto().apply {
                    eventName = "logout"
                    stepNumber = 2
                    stepConditions = arrayListOf(
                        ConditionCreateDto().apply {
                            eventProperty = "hhh"
                            eventPropertyType = PropertyType.TEXT
                            value = "123"
                            negate = false
                            operator = Operator.GREATER_THAN_OR_EQUAL
                        }
                    )
                }
            )
        }
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val result = repo.findAll().toList()
        assert(result.size == 1)

        funnelDto.name = "funnel 1 2"
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        funnelDto.name = "funnel 2 3"
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        funnelDto.name = "funnel 2 4"
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()


        mockMvc.sendGet("/api/web/analytics/funnel", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
            .andReturn()


        mockMvc.sendGet("/api/web/analytics/funnel/contains/1?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(2))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/2?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(3))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/4?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(1))
            .andReturn()

        client = initialClient("Intrack2")

        funnelDto.name = "second cro funnel"
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        funnelDto.name = "third cro funnel"
        mockMvc.sendPost("/api/web/analytics/funnel", funnelDto, client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel", client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
            .andReturn()


        mockMvc.sendGet("/api/web/analytics/funnel/contains/second?page=0&size=10", client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(1))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/third?page=0&size=10", client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(1))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/fun?page=0&size=10", client?.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(2))
            .andReturn()
    }
}