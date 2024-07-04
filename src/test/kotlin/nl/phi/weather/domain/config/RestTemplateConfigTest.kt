package nl.phi.weather.domain.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.web.client.RestTemplate
import kotlin.test.assertNotNull

@SpringBootTest
@Import(RestTemplateConfig::class)
class RestTemplateConfigTest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Test
    fun `restTemplate bean exists in context`() {
        assertNotNull(restTemplate, "RestTemplate bean should not be null")
    }
}
