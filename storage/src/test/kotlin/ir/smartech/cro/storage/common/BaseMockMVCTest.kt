package ir.smartech.cro.storage.common

import com.fasterxml.jackson.databind.ObjectMapper
import ir.smartech.cro.storage.StorageApplication
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@AutoConfigureMockMvc
@SpringBootTest(classes = [StorageApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
open class BaseMockMVCTest : BaseTest() {

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var applicationContext: WebApplicationContext

    val objectMapper = ObjectMapper()

    @BeforeEach
    fun beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
            .build()
    }

    fun MockMvc.sendPost(url: String, body: String): ResultActions {
        return this.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
    }
}