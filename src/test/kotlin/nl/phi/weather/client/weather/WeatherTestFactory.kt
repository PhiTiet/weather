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
                weathercode = "wmo code",
                temperature = "°C",
                windspeed = "km/h",
                isDay = "1",
                interval = "1",
                time = "2024-06-24T12:00:00",
                winddirection = "°"
            )
        }

        fun mockCurrentWeather(): CurrentWeather {
            return CurrentWeather(
                weathercode = 1,
                temperature = 20.0,
                windspeed = 5.0,
                isDay = 1,
                interval = 1,
                time = "2024-06-24T12:00:00",
                winddirection = 270
            )
        }
    }
}