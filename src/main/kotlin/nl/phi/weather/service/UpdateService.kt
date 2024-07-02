package nl.phi.weather.service

import nl.phi.weather.client.discord.DiscordClient
import nl.phi.weather.client.weather.WeatherClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UpdateService(
    private val emailService: EmailService,
    private val weatherClient: WeatherClient,
    private val discordClient: DiscordClient

) {
    companion object {
        private val LOG: Logger = Logger.getLogger(UpdateService::class.java.name)
    }

    @Scheduled(cron = "0 0 8 * * ?", zone = "Europe/Amsterdam")
    fun sendDailyUpdate() {
        val weather = weatherClient.getWeatherForHoorn()
        if (weather.isSwimmingWeather()) {
            emailService.sendSwimmingUpdate(weather)
            discordClient.postMessage("Swim Alert: ${weather.getFullWeatherMessage()}")
            LOG.info("Swimming update sent")
        } else {
            emailService.sendDailyUpdate(weather)
            LOG.info("Daily update sent")
        }
    }
}