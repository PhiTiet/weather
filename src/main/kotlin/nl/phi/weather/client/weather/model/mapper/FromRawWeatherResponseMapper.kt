package nl.phi.weather.client.weather.model.mapper

import nl.phi.weather.client.weather.model.CurrentWeatherUnits
import nl.phi.weather.client.weather.model.RawWeatherResponse
import nl.phi.weather.domain.Weather
import nl.phi.weather.domain.WeatherType

class FromRawWeatherResponseMapper {

    fun map(rawWeatherResponse: RawWeatherResponse): Weather {
        rawWeatherResponse.currentWeatherUnits?.let { assertCorrectUnits(it) }
        val currentWeather = rawWeatherResponse.currentWeather
            ?: throw RuntimeException("Current weather data is missing")

        return Weather(
            weatherType = currentWeather.weathercode?.let { WeatherType.fromCode(it) }
                ?: throw RuntimeException("Weather code is missing"),
            temperature = currentWeather.temperature
                ?: throw RuntimeException("Temperature is missing"),
            windSpeed = currentWeather.windspeed
                ?: throw RuntimeException("Windspeed is missing"),
            windDirection = currentWeather.winddirection?.toDouble()?.let { degreesToCompassDirection(it) }
                ?: throw RuntimeException("Wind direction is missing")
        )
    }

    private fun assertCorrectUnits(currentWeatherUnits: CurrentWeatherUnits) {
        require(currentWeatherUnits.temperature == "°C") { "Temperature unit is not °C" }
        require(currentWeatherUnits.windspeed == "km/h") { "Windspeed unit is not km/h" }
        require(currentWeatherUnits.weathercode == "wmo code") { "Weather code unit is not wmo code" }
        require(currentWeatherUnits.winddirection == "°") { "Wind direction unit is not °" } }

    private fun degreesToCompassDirection(degrees: Double): String {
        val directions = arrayOf(
            "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
        )
        val index = ((degrees / 22.5) + 0.5).toInt() % 16
        return directions[index]
    }
}
