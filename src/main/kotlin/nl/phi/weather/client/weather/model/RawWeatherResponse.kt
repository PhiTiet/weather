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
    val weathercode: String? = null,

    @JsonProperty("temperature")
    val temperature: String? = null,

    @JsonProperty("windspeed")
    val windspeed: String? = null,

    @JsonProperty("is_day")
    val isDay: String? = null,

    @JsonProperty("interval")
    val interval: String? = null,

    @JsonProperty("time")
    val time: String? = null,

    @JsonProperty("winddirection")
    val winddirection: String? = null
)

data class CurrentWeather(
    @JsonProperty("weathercode")
    val weathercode: Int? = null,

    @JsonProperty("temperature")
    val temperature: Double? = null,

    @JsonProperty("windspeed")
    val windspeed: Double? = null,

    @JsonProperty("is_day")
    val isDay: Int? = null,

    @JsonProperty("interval")
    val interval: Int? = null,

    @JsonProperty("time")
    val time: String? = null,

    @JsonProperty("winddirection")
    val winddirection: Int? = null
)
