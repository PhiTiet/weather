package nl.phi.weather

import nl.phi.weather.client.discord.DiscordClient
import nl.phi.weather.service.EmailService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class WeatherApplicationTests {
    @MockBean
    private lateinit var discordClient : DiscordClient

    @MockBean
    private lateinit var emailService: EmailService

    @Test
    fun contextLoads() {
    }

}
