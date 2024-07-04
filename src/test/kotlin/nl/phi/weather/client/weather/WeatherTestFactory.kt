package nl.phi.weather.client.weather

import nl.phi.weather.client.weather.model.CurrentWeather
import nl.phi.weather.client.weather.model.CurrentWeatherUnits
import nl.phi.weather.client.weather.model.RawWeatherResponse

class WeatherTestFactory {
    companion object {
        fun mockRawWeatherResponse(): RawWeatherResponse {
            return RawWeatherResponse(
                currentWeatherUnits = mockCurrentWeatherUnits(),
                currentWeather = mockCurrentWeather()
            )
        }

        fun mockCurrentWeatherUnits(): CurrentWeatherUnits {
            return CurrentWeatherUnits(
                weatherCode = "wmo code",
                temperature = "°C",
                windSpeed = "km/h",
                isDay = "1",
                interval = "1",
                time = "2024-06-24T12:00:00",
                windDirection = "°"
            )
        }

        fun mockCurrentWeather(): CurrentWeather {
            return CurrentWeather(
                weatherCode = 1,
                temperature = 20.0,
                windSpeed = 5.0,
                isDay = 1,
                interval = 1,
                time = "2024-06-24T12:00:00",
                windDirection = 270
            )
        }
    }
}