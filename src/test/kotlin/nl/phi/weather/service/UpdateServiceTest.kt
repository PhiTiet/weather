package nl.phi.weather.service

import nl.phi.weather.client.discord.DiscordClient
import nl.phi.weather.client.weather.WeatherClient
import nl.phi.weather.domain.Weather
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UpdateServiceTest {

    @Mock
    private lateinit var emailService: EmailService

    @Mock
    private lateinit var weatherClient: WeatherClient

    @Mock
    private lateinit var discordClient: DiscordClient

    @InjectMocks
    private lateinit var updateService: UpdateService

    @Test
    fun `sendDailyUpdate sends swimming update when weather is suitable for swimming`() {
        val weather = mock(Weather::class.java)
        `when`(weatherClient.getWeatherForHoorn()).thenReturn(weather)
        `when`(weather.isSwimmingWeather()).thenReturn(true)
        `when`(weather.getFullWeatherMessage()).thenReturn("Sunny and warm")

        updateService.sendDailyUpdate()

        verify(emailService).sendSwimmingUpdate(weather)
        verify(discordClient).postMessage("Swim Alert: Sunny and warm")
    }

    @Test
    fun `sendDailyUpdate sends daily update when weather is not suitable for swimming`() {
        val weather = mock(Weather::class.java)
        `when`(weatherClient.getWeatherForHoorn()).thenReturn(weather)
        `when`(weather.isSwimmingWeather()).thenReturn(false)

        updateService.sendDailyUpdate()

        verify(emailService).sendDailyUpdate(weather)
        verify(discordClient, never()).postMessage(anyString())
    }
}
