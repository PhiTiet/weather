package nl.phi.weather.service

import nl.phi.weather.domain.Weather
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class EmailServiceTest {
    private val mailSender: JavaMailSender = mock()
    private val dailyUpdateEmail = "daily@example.com"
    private val swimmingUpdateEmails = listOf("swimming1@example.com", "swimming2@example.com")
    private val emailService = EmailService(mailSender, dailyUpdateEmail, swimmingUpdateEmails)

    @Test
    fun `test sendDailyUpdate sends email with correct content`() {
        val weather = mock<Weather>()
        `when`(weather.getWeatherReport()).thenReturn("Sunny with a chance of rain")
        `when`(weather.getWeatherHeading()).thenReturn("Sunny Day")

        emailService.sendDailyUpdate(weather)

        val message = SimpleMailMessage().apply {
            setTo(dailyUpdateEmail)
            setText("Sunny with a chance of rain")
            setSubject("Today's weather: Sunny Day")
        }
        Mockito.verify(mailSender).send(message)
    }

    @Test
    fun `test sendSwimmingUpdate sends email with correct content`() {
        val weather = mock<Weather>()
        `when`(weather.getWeatherReport()).thenReturn("Perfect for a swim")
        `when`(weather.getWeatherHeading()).thenReturn("Great Swimming Weather")

        emailService.sendSwimmingUpdate(weather)

        val message = SimpleMailMessage().apply {
            setTo(*swimmingUpdateEmails.toTypedArray())
            setText("Perfect for a swim")
            setSubject("Swimming weather! Swimstrim time bro: Great Swimming Weather")
        }
        Mockito.verify(mailSender).send(message)
    }
}
