package nl.phi.weather.client.weather

import nl.phi.weather.client.weather.model.RawWeatherResponse
import nl.phi.weather.client.weather.model.mapper.FromRawWeatherResponseMapper
import nl.phi.weather.domain.Weather
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

private const val HOORN_LATITUDE = 52.6424
private const val HOORN_LONGITUDE = 5.0602

@Component
class WeatherClient(private val restTemplate: RestTemplate) {
    private val weatherUrl = "https://api.open-meteo.com/v1/forecast"
    private val fromRawWeatherResponseMapper = FromRawWeatherResponseMapper()

    fun getWeatherForHoorn(): Weather {
        return getWeather(HOORN_LATITUDE, HOORN_LONGITUDE)
    }

    fun getWeather(latitude: Double, longitude: Double): Weather {
        val uri = UriComponentsBuilder.fromHttpUrl(weatherUrl)
            .queryParam("latitude", latitude)
            .queryParam("longitude", longitude)
            .queryParam("current_weather", true)
            .toUriString()

        val response = checkNotNull( restTemplate.getForObject(uri, RawWeatherResponse::class.java)) {"Failed to fetch weather data"}

        return fromRawWeatherResponseMapper.map(response)
    }
}
