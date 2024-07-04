package nl.phi.weather.client.weather.model.mapper

import nl.phi.weather.client.weather.model.CurrentWeatherUnits
import nl.phi.weather.client.weather.model.RawWeatherResponse
import nl.phi.weather.domain.Weather
import nl.phi.weather.domain.WeatherType

class FromRawWeatherResponseMapper {

    fun map(rawWeatherResponse: RawWeatherResponse): Weather {
        rawWeatherResponse.currentWeatherUnits?.let { assertCorrectUnits(it) }

        val currentWeather = checkNotNull(rawWeatherResponse.currentWeather) { "Current weather data is missing" }

        return Weather(
            weatherType = checkNotNull(currentWeather.weatherCode?.let { WeatherType.fromCode(it) })
            { "Weather code is missing" },
            temperature = checkNotNull(currentWeather.temperature)
            { "Temperature is missing" },
            windSpeed = checkNotNull(currentWeather.windSpeed)
            { "Windspeed is missing" },
            windDirection = checkNotNull(currentWeather.windDirection?.toDouble()?.let { degreesToCompassDirection(it)})
            { "Wind direction is missing" }
        )
    }

    private fun assertCorrectUnits(currentWeatherUnits: CurrentWeatherUnits) {
        require(currentWeatherUnits.temperature == "°C") { "Temperature unit is not °C" }
        require(currentWeatherUnits.windSpeed == "km/h") { "Windspeed unit is not km/h" }
        require(currentWeatherUnits.weatherCode == "wmo code") { "Weather code unit is not wmo code" }
        require(currentWeatherUnits.windDirection == "°") { "Wind direction unit is not °" }
    }

    private fun degreesToCompassDirection(degrees: Double): String {
        val directions = arrayOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
        )
        val index = ((degrees / 22.5) + 0.5).toInt() % 16
        return directions[index]
    }
}
