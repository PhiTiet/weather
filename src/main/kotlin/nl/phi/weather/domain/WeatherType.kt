package nl.phi.weather.domain

enum class WeatherType(val code: Int, val description: String) {
    CLEAR_SKY(0, "Clear Sky"),
    MAINLY_CLEAR(1, "Mainly clear"),
    PARTLY_CLOUDY(2, "Partly cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing rime fog"),
    DRIZZLE_LIGHT(51, "Drizzle - Light intensity"),
    DRIZZLE_MODERATE(53, "Drizzle - Moderate intensity"),
    DRIZZLE_DENSE(55, "Drizzle - Dense intensity"),
    FREEZING_DRIZZLE_LIGHT(56, "Freezing Drizzle - Light intensity"),
    FREEZING_DRIZZLE_DENSE(57, "Freezing Drizzle - Dense intensity"),
    RAIN_SLIGHT(61, "Rain - Slight intensity"),
    RAIN_MODERATE(63, "Rain - Moderate intensity"),
    RAIN_HEAVY(65, "Rain - Heavy intensity"),
    FREEZING_RAIN_LIGHT(66, "Freezing Rain - Light intensity"),
    FREEZING_RAIN_HEAVY(67, "Freezing Rain - Heavy intensity"),
    SNOW_FALL_SLIGHT(71, "Snow fall - Slight intensity"),
    SNOW_FALL_MODERATE(73, "Snow fall - Moderate intensity"),
    SNOW_FALL_HEAVY(75, "Snow fall - Heavy intensity"),
    SNOW_GRAINS(77, "Snow grains"),
    RAIN_SHOWERS_SLIGHT(80, "Rain showers - Slight intensity"),
    RAIN_SHOWERS_MODERATE(81, "Rain showers - Moderate intensity"),
    RAIN_SHOWERS_VIOLENT(82, "Rain showers - Violent intensity"),
    SNOW_SHOWERS_SLIGHT(85, "Snow showers - Slight intensity"),
    SNOW_SHOWERS_HEAVY(86, "Snow showers - Heavy intensity"),
    THUNDERSTORM_SLIGHT(95, "Thunderstorm - Slight"),
    THUNDERSTORM_WITH_HAIL_SLIGHT(96, "Thunderstorm with hail - Slight"),
    THUNDERSTORM_WITH_HAIL_HEAVY(99, "Thunderstorm with hail - Heavy");

    companion object {
        private val map = entries.associateBy(WeatherType::code)

        fun fromCode(code: Int?): WeatherType {
            return map[code] ?: throw IllegalArgumentException("Unknown weather code: $code")
        }
    }

    fun isSwimmingWeatherType(): Boolean {
        return (this == MAINLY_CLEAR) or (this == PARTLY_CLOUDY) or  (this == CLEAR_SKY)
    }
}
