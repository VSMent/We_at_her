package pr.eleks.we_at_her.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.entities.WeatherSample
import pr.eleks.we_at_her.services.data.WeatherSampleService
import java.util.stream.Collectors

@RestController
@RequestMapping("/REST")
class WeatherSampleController(
        private val weatherService: WeatherSampleService,
        private val mapper: ObjectMapper
) {

    @GetMapping("/weatherSample")
    fun getAllWeatherSamples(): List<WeatherSampleDto?> =
            weatherService.getAllWeatherSamples().stream()
                    .map(::convertToDto)
                    .collect(Collectors.toList())


    private fun convertToDto(weatherSample: WeatherSample?) =
            if (weatherSample == null)
                null
            else mapper.convertValue(weatherSample, WeatherSampleDto::class.java)

}