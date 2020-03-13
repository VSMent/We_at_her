package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pr.eleks.we_at_her.dto.weather.WeatherBitApiDto
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.exceptions.WrongApiResponseException

@Service
class WeatherBitApiServiceImpl(
        private val restTemplate: RestTemplate,
        override var env: Environment
) : AbstractApiServiceImpl() {
    override fun getName(): String = "WBApi"

    override fun getWeatherSampleFromApi(latitude: String?, longitude: String?, lang: String?, units: String?): WeatherSampleDto {
        prepareParameters(latitude, longitude, lang, units)
        prepareBaseUrl()

        // Prepare request string
        uriBuilder
                .queryParam("lat", defaultValues["latitude"])
                .queryParam("lon", defaultValues["longitude"])
                .queryParam("lang", defaultValues["lang"])
                .queryParam("units", defaultValues["units"])
                .queryParam("key", env.getProperty(apiPrefix + getName() + ".key")
                        ?: throw PropertyNotFoundException(apiPrefix + getName() + ".key"))

        // Make request
        val apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), WeatherBitApiDto::class.java)
                ?: throw WrongApiResponseException(WeatherBitApiDto::class.java.name)

        // Return result
        return WeatherSampleDto(
                apiResponseDto.cityName,
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
