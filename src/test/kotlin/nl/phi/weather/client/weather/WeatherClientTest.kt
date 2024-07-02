package nl.phi.weather.client.weather

import nl.phi.weather.client.weather.WeatherTestFactory.Companion.mockRawWeatherResponse
import nl.phi.weather.client.weather.model.RawWeatherResponse
import nl.phi.weather.domain.Weather
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
class WeatherClientTest {

    @Mock
    private lateinit var restTemplate: RestTemplate
    
    @InjectMocks
    private lateinit var weatherClient: WeatherClient

    @Test
    fun `test getWeather`() {
        val latitude = 52.6424
        val longitude = 5.0602
        val rawWeatherResponse = mockRawWeatherResponse()

        Mockito.`when`(restTemplate.getForObject(anyString(), Mockito.eq(RawWeatherResponse::class.java)))
            .thenReturn(rawWeatherResponse)

        val result = weatherClient.getWeather(latitude, longitude)
        assertThat(result).isInstanceOf(Weather::class.java)
    }

}
