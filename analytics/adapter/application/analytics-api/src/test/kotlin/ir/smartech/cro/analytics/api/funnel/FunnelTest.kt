package ir.smartech.cro.analytics.api.funnel

import BaseMockMVCTest
import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.analytics.api.dto.funnel.FunnelViewDto
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import ir.smartech.cro.analytics.rdb.repository.JpaClientRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class FunnelTest(
    @Autowired
    private val repo: JpaFunnelRepository,
    @Autowired
    private val clientRepository: JpaClientRepository,
    @Autowired
    private val objectMapper: ObjectMapper
) : BaseMockMVCTest() {

    @BeforeEach
    fun clearDb() {
        repo.deleteAll()
        clientRepository.deleteAll()
    }

    @Test
    fun `create funnel`() {
        var json = """
            {
              "name": "Intrack",
              "description" : "test test",
              "enabled": true
            }
        """.trimIndent()


        mockMvc.sendPost("/api/web/analytics/client", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val client = clientRepository.findAll().first()

        json = """
            {
              "productNumber": 2,
              "name": "first cro funnel",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("first cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andReturn()

        val result = repo.findAll().toList()
        assert(result.size == 1)
    }


    @Test
    fun `create and update`() {
        var json = """
            {
              "name": "Intrack",
              "description" : "test test",
              "enabled": true
            }
        """.trimIndent()


        mockMvc.sendPost("/api/web/analytics/client", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val client = clientRepository.findAll().first()

        json = """
            {
              "productNumber": 2,
              "name": "second cro funnel",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("second cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepConditions.size()").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepConditions.size()").value(1))
            .andReturn()

        var result = repo.findAll().toList()
        assert(result.size == 1)

        json = """
            {
              "id":${result[0].id},
              "productNumber": 6,
              "name": "update ${result[0].name}",
              "steps": [
                {
                  "eventName": "update login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhhwww",
                      "eventPropertyType": "NUMBER",
                      "value": "1222234444",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhhttt",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4666",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhhuuuu",
                      "eventPropertyType": "TEXT",
                      "value": "1223888",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "updated logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhhccc",
                      "eventPropertyType": "TEXT",
                      "value": "12345",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPut("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("update ${result[0].name}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.productNumber").value(6))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(result[0].id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("update login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("updated logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(1))
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
        var json = """
            {
              "name": "Intrack",
              "description" : "test",
              "enabled": true
            }
        """.trimIndent()


        mockMvc.sendPost("/api/web/analytics/client", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val client = clientRepository.findAll().first()

        json = """
            {
              "productNumber": 2,
              "name": "second cro funnel",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("second cro funnel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].eventName").value("login"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].eventName").value("logout"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[0].stepNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.steps[1].stepNumber").value(1))
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
        assert(entity.steps?.get(0)?.stepNumber == 0)
        assert(entity.steps?.get(1)?.stepNumber == 1)
        assert(entity.steps?.get(0)?.stepConditions?.size == 3)
        assert(entity.steps?.get(1)?.stepConditions?.size == 1)

    }


    @Test
    fun `create and getAll and getAllByContains funnel`() {
        var json = """
            {
              "name": "Intrack",
              "description" : "test",
              "enabled": true
            }
        """.trimIndent()


        mockMvc.sendPost("/api/web/analytics/client", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        var client = clientRepository.findAll().first()

        json = """
            {
              "productNumber": 2,
              "name": "funnel 1",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val result = repo.findAll().toList()
        assert(result.size == 1)

        json = """
            {
              "productNumber": 2,
              "name": "funnel 1 2",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        json = """
            {
              "productNumber": 2,
              "name": "funnel 2 3",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        json = """
            {
              "productNumber": 2,
              "name": "funnel 2 4",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
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

        json = """
            {
              "name": "Intrack2",
              "description" : "test",
              "enabled": true
            }
        """.trimIndent()


        mockMvc.sendPost("/api/web/analytics/client", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        client = clientRepository.findAll().first { it.name == "Intrack2" }

        json = """
            {
              "productNumber": 2,
              "name": "second cro funnel",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        json = """
            {
              "productNumber": 2,
              "name": "third cro funnel",
              "steps": [
                {
                  "eventName": "login",
                  "stepNumber": 0,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "122223",
                      "negate": "true",
                      "operator": "EQUAL"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "NUMBER",
                      "value": "12223,4",
                      "negate": "true",
                      "operator": "NOT_BETWEEN"
                    },
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "1223",
                      "negate": "true",
                      "operator": "ENDS_WITH"
                    }
                  ]
                },
                {
                  "eventName": "logout",
                  "stepNumber": 1,
                  "stepConditions": [
                    {
                      "eventProperty": "hhh",
                      "eventPropertyType": "TEXT",
                      "value": "123",
                      "negate": "false",
                      "operator": "GREATER_THAN_OR_EQUAL"
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/analytics/funnel", json, client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
            .andReturn()


        mockMvc.sendGet("/api/web/analytics/funnel/contains/second?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(1))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/third?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(1))
            .andReturn()

        mockMvc.sendGet("/api/web/analytics/funnel/contains/fun?page=0&size=10", client.id!!)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(2))
            .andReturn()
    }
}