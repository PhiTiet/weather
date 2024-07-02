package nl.phi.weather.client.discord

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class DiscordClientTest {

    private val restTemplate = Mockito.mock(RestTemplate::class.java)

    private val discordClient: DiscordClient = DiscordClient(restTemplate, "testUrl")

    @Test
    fun `test postMessage`() {
        val message = "Hello, Discord!"
        val responseEntity = ResponseEntity("OK", HttpStatus.OK)

        Mockito.`when`(
            restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(HttpMethod.POST),
                ArgumentMatchers.any(HttpEntity::class.java),
                ArgumentMatchers.eq(String::class.java)
            )
        ).thenReturn(responseEntity)

        val statusCode = discordClient.postMessage(message)

        assertEquals(HttpStatus.OK, statusCode)
    }
}
