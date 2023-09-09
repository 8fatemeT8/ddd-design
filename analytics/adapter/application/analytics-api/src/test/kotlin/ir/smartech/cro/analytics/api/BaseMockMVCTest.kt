import ir.smartech.cro.analytics.adapter.compositionroot.CompositionConfig
import ir.smartech.cro.analytics.api.AnalyticsApplication
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Import(CompositionConfig::class)
@AutoConfigureMockMvc
@SpringBootTest(classes = [AnalyticsApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BaseMockMVCTest {
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var applicationContext: WebApplicationContext

    @BeforeEach
    fun beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
            .build()
    }

    fun MockMvc.sendPost(url: String, body: String, clientId: Int? = null): ResultActions {
        return this.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Client-id", clientId ?: "1")
        )
    }

    fun MockMvc.sendGet(url: String, clientId: Int? = null): ResultActions {
        return this.perform(
            MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Client-id", clientId ?: "1")
        )
    }

    fun MockMvc.sendDelete(url: String, clientId: Int? = null): ResultActions {
        return this.perform(
            MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Client-id", clientId ?: "1")
        )
    }

    fun MockMvc.sendPut(url: String, body: String, clientId: Int? = null): ResultActions {
        return this.perform(
            MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Client-id", clientId ?: "1")
        )
    }
}