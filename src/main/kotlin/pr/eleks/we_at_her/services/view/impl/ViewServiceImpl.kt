package pr.eleks.we_at_her.services.view.impl

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.services.EmailService
import pr.eleks.we_at_her.services.view.ViewService

@Service
class ViewServiceImpl(
        private val env: Environment,
        private val restTemplate: RestTemplate,
        private val emailService: EmailService
) : ViewService {
    override fun hostUriBuilder(): UriComponentsBuilder =
            UriComponentsBuilder
                    .fromUriString((env.getProperty("server.host")
                            ?: throw PropertyNotFoundException("server.host"))
                            + ":" +
                            (env.getProperty("server.port")
                                    ?: throw PropertyNotFoundException("server.port"))
                    )

    override fun getAllWeatherSamples(): List<WeatherSampleDto?> {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("weatherSample")
                .toUriString()
        // Make request
        return restTemplate.getForObject(requestUri, Array<WeatherSampleDto?>::class.java)?.toList()
                ?: emptyList()
    }

    override fun getAllCities(): List<CityDto?> {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("city")
                .toUriString()
        // Make request
        return restTemplate.getForObject(requestUri, Array<CityDto?>::class.java)?.toList()
                ?: emptyList()
    }

    override fun createUser(userDto: UserDto): UserDto? {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("user")
                .toUriString()

        val savedUser = restTemplate.postForEntity(requestUri, userDto, UserDto::class.java).body
        if (savedUser != null) {
            val activateUri = hostUriBuilder()
                    .pathSegment("activate")
                    .queryParam("u", savedUser.uuid.toString())
                    .toUriString()
            emailService.sendEmail(savedUser.email!!,
                    "Your account was registered",
                    """
                    |Dear ${savedUser.username}.
                    |Your account was successfully created.
                    |To activate account please go to $activateUri .
                    """.trimMargin())
        }
        return savedUser
    }

    override fun getAllBlogPosts(): List<BlogPostDto?> {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("blogPost")
                .toUriString()
        // Make request
        return restTemplate.getForObject(requestUri, Array<BlogPostDto?>::class.java)?.toList()
                ?: emptyList()
    }

    override fun activateUser(userUuid: String): UserDto? {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("activateUser")
                .pathSegment(userUuid)
                .toUriString()

        return restTemplate.getForObject(requestUri, UserDto::class.java)
    }

    override fun createNewBlogPost(blogPostDto: BlogPostDto): BlogPostDto? {
        val requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("blogPost")
                .toUriString()

        return restTemplate.postForEntity(requestUri, blogPostDto, BlogPostDto::class.java).body
    }
}