package nl.phi.weather.domain

data class Weather(
    private val weatherType: WeatherType,
    private val temperature: Double,
    private val windSpeed: Double,
    private val windDirection: String
) {
    fun isSwimmingWeather(): Boolean = weatherType.isSwimmingWeatherType() and (temperature > 15.0)

    fun getWeatherReport(): String {
        return "Good morning, \n" +
                "Today's weather is ${weatherType.description}. It's $temperature°C with a wind speed" +
                " of $windSpeed km/h $windDirection.\n" +
                "Cheers, Kotlin Weather-bot"
    }

    fun getWeatherHeading(): String = "${weatherType.description} $temperature°C"

    fun getFullWeatherMessage(): String {
        return "${getWeatherHeading()} \n ${getWeatherReport()}"
    }
}
