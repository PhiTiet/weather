package nl.phi.weather.client.weather.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RawWeatherResponse(
    @JsonProperty("current_weather_units")
    val currentWeatherUnits: CurrentWeatherUnits? = null,

    @JsonProperty("current_weather")
    val currentWeather: CurrentWeather? = null
)

data class CurrentWeatherUnits(
    @JsonProperty("weathercode")
    val weatherCode: String? = null,

    @JsonProperty("temperature")
    val temperature: String? = null,

    @JsonProperty("windspeed")
    val windSpeed: String? = null,

    @JsonProperty("is_day")
    val isDay: String? = null,

    @JsonProperty("interval")
    val interval: String? = null,

    @JsonProperty("time")
    val time: String? = null,

    @JsonProperty("winddirection")
    val windDirection: String? = null
)

data class CurrentWeather(
    @JsonProperty("weathercode")
    val weatherCode: Int? = null,

    @JsonProperty("temperature")
    val temperature: Double? = null,

    @JsonProperty("windspeed")
    val windSpeed: Double? = null,

    @JsonProperty("is_day")
    val isDay: Int? = null,

    @JsonProperty("interval")
    val interval: Int? = null,

    @JsonProperty("time")
    val time: String? = null,

    @JsonProperty("winddirection")
    val windDirection: Int? = null
)
