package pr.eleks.we_at_her.services.view

import org.springframework.web.util.UriComponentsBuilder
import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException

interface ViewService {
    fun hostUriBuilder(): UriComponentsBuilder
    fun getAllWeatherSamples(): List<WeatherSampleDto?>
    fun getAllCities(): List<CityDto?>
    fun createUser(userDto: UserDto): UserDto?
    fun getAllBlogPosts(): List<BlogPostDto?>
    fun activateUser(userUuid: String): UserDto?
    fun createNewBlogPost(blogPostDto: BlogPostDto): BlogPostDto?
}