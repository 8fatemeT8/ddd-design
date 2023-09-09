package ir.smartech.cro.storage

import ir.smartech.cro.storage.common.BaseMockMVCTest
import ir.smartech.cro.storage.config.WithMockUserDto
import ir.smartech.cro.storage.config.kafka.KafkaTopic
import ir.smartech.cro.storage.data.postgres.repository.ProjectSchemaRepository
import ir.smartech.cro.storage.data.postgres.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class CollectorTest(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val projectSchemaRepository: ProjectSchemaRepository,
) : BaseMockMVCTest() {


    @AfterEach
    fun afterTest() {
        projectSchemaRepository.deleteAll()
        userRepository.deleteAll()
    }

    @BeforeEach
    fun getToken() {
        val json = """
            {
              "username": "intract_project_1",
              "password": "111111",
              "name": "Intrack"
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/account/register", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        return
    }

    @Test
    @WithMockUserDto(username = "intract_project_1", password = "111111")
    fun ` test saving schema and then receive data also saving in kafka `() {
        // add user schema
        var json = """
            {
              "data": {
                "productId":"NUMBER",
                "appVersion": "STRING",
                "deviceType": "NUMBER",
                "active":"BOOLEAN",
                "eventAttributes":"JSON"
              }
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/user/schema", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assert(projectSchemaRepository.findAll().size == 1)


        // receive api
        json = """
            {
              "productId": 2,
              "appVersion": "1.9.1",
              "deviceType": "ff",
              "eventAttributes" : "{\"hi\":\"aaa\"}",
              "active":false
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isNotAcceptable)
            .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("send deviceType value with NUMBER type"))
            .andReturn()


        json = """
            {
              "appVersion": "1.9.1",
              "deviceType": "ff",
              "eventAttributes" : "{\"hi\":\"aaa\"}",
              "active":false
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isNotAcceptable)
            .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("the productId must not be null"))
            .andReturn()

        json = """
            {
              "productId": 2,
              "appVersion": "1.9.1",
              "deviceType": 12,
              "eventAttributes" : "hi",
              "active":false
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isNotAcceptable)
            .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("send eventAttributes value with JSON type"))
            .andReturn()

        json = """
            {
              "productId": 2,
              "appVersion": "1.9.1",
              "deviceType": 12,
              "eventAttributes" : "{\"hi\":\"aaa\"}",
              "active": "hello"
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isNotAcceptable)
            .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("send active value with BOOLEAN type"))
            .andReturn()

        json = """
            {
              "productId": 2,
              "appVersion": "1.9.1",
              "deviceType": 12,
              "eventAttributes" : "{\"hi\":\"aaa1\"}",
              "active": false
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        var kafkaMessage = getSingleRecord(KafkaTopic.gatewayEmit).value() as? HashMap<String?, String?>
        validateKafkaMessage(json, kafkaMessage)

        json = """
            {
              "productId": 3,
              "deviceType": 13,
              "eventAttributes" : "{\"hi\":\"aaa2\"}",
              "active": true
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        kafkaMessage = getSingleRecord(KafkaTopic.gatewayEmit).value() as? HashMap<String?, String?>
        validateKafkaMessage(json, kafkaMessage)

        json = """
            {
              "productId": 4,
              "appVersion": "1.9.2",
              "deviceType": 14,
              "eventAttributes" : "{\"hi\":\"aaa3\"}",
              "active": false
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        kafkaMessage = getSingleRecord(KafkaTopic.gatewayEmit).value() as? HashMap<String?, String?>
        validateKafkaMessage(json, kafkaMessage)

        json = """
            {
              "productId": 4,
              "appVersion": null,
              "deviceType": null,
              "eventAttributes" : null,
              "active": null
            }
        """.trimIndent()

        mockMvc.sendPost("/api/web/cro/collector/receive", json)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        kafkaMessage = getSingleRecord(KafkaTopic.gatewayEmit).value() as? HashMap<String?, String?>
        validateKafkaMessage(json, kafkaMessage)


    }

    private fun validateKafkaMessage(json: String, kafkaMessage: HashMap<String?, String?>?) {
        if (kafkaMessage == null) assert(false)
        objectMapper.readValue(json, HashMap::class.java)
            ?.forEach { (k, v) ->
                if (kafkaMessage?.get(k) != v?.toString()) assert(false)
            }
    }
}