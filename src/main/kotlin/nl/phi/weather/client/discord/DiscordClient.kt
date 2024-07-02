package nl.phi.weather.client.discord

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class DiscordClient(
    private val restTemplate: RestTemplate,
    @Value("\${discord.webhook.url}") private val url: String
) {

    fun postMessage(message: String): HttpStatusCode {
        val headers = HttpHeaders().apply {
            contentType = APPLICATION_JSON
        }

        val payload = mapOf("content" to message)
        val request = HttpEntity(payload, headers)

        val response: ResponseEntity<String> = restTemplate.exchange(
            url, POST, request, String::class.java
        )

        println("Response: ${response.statusCode} - ${response.body}")
        return response.statusCode
    }

    @Scheduled(initialDelay = 60 * 1000)
    fun sendStartup(){
        println("Sending startup message to: ${url}" )
        postMessage("Startup completed")
    }
}
