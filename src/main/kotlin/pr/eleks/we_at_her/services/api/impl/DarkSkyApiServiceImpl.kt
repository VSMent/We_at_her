package pr.eleks.we_at_her.services.api.impl

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pr.eleks.we_at_her.dto.weather.DarkSkyApiDto
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.exceptions.WrongApiResponseException

@Service
class DarkSkyApiServiceImpl(
        private val restTemplate: RestTemplate,
        override var env: Environment
) : AbstractApiServiceImpl() {
    override fun getName(): String = "DSApi"

    override fun getWeatherSampleFromApi(latitude: String?, longitude: String?, lang: String?, units: String?): WeatherSampleDto {
        prepareParameters(latitude, longitude, lang, units)
        prepareBaseUrl()

        // Prepare request string
        uriBuilder
                .pathSegment(env.getProperty(apiPrefix + getName() + ".key")
                        ?: throw PropertyNotFoundException(apiPrefix + getName() + ".key"))
                .pathSegment(defaultValues["latitude"] + "," + defaultValues["longitude"])
                .queryParam("lang", defaultValues["lang"])
                .queryParam("units", defaultValues["units"])
                .queryParam("exclude", env.getProperty(apiPrefix + getName() + ".exclude")
                        ?: throw PropertyNotFoundException(apiPrefix + getName() + ".exclude"))

        // Make request
        val apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), DarkSkyApiDto::class.java)
                ?: throw WrongApiResponseException(DarkSkyApiDto::class.java.name)

        // Return result
        return WeatherSampleDto(
                null,
                apiResponseDto.temperature,
                apiResponseDto.feelsLike,
                apiResponseDto.pressure,
                apiResponseDto.humidity,
                apiResponseDto.clouds,
                -1,
                apiResponseDto.time,
                apiResponseDto.latitude,
                apiResponseDto.longitude
        )
    }
}
