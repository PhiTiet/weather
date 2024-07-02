package nl.phi.weather.service

import nl.phi.weather.domain.Weather
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val mailSender: JavaMailSender,
    @Value("\${email.daily.update}") private val dailyUpdateEmail: String,
    @Value("\${email.swimming.update}") private val swimmingUpdateEmails : List<String>
) {

    fun sendDailyUpdate(weather: Weather) {
        val message = SimpleMailMessage().apply {
            setTo(dailyUpdateEmail)
            setText(weather.getWeatherReport())
            setSubject("Today's weather: ${weather.getWeatherHeading()}")
        }
        mailSender.send(message)
    }

    fun sendSwimmingUpdate(weather: Weather) {
        val message = SimpleMailMessage().apply {
            setTo(*swimmingUpdateEmails.toTypedArray())
            setText(weather.getWeatherReport())
            setSubject("Swimming weather! Swimstrim time bro: ${weather.getWeatherHeading()}")
        }
        mailSender.send(message)
    }
}

