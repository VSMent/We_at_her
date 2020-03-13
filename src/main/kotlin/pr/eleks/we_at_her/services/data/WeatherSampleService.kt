package pr.eleks.we_at_her.services.data

import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.entities.WeatherSample
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException
import pr.eleks.we_at_her.exceptions.WrongApiResponseException
import java.util.*

interface WeatherSampleService {
    fun getAllWeatherSamples(): List<WeatherSample?>

    fun findFirstWeatherSampleByCityIdAndTime(cityId: Int, time: Int): WeatherSample?

    fun addWeatherSample(weatherSample: WeatherSample): WeatherSample

    fun getAverageFromWeatherSamples(apiDtos: List<WeatherSampleDto>): WeatherSampleDto

    @Throws(PropertyNotFoundException::class, UnknownServiceNameException::class, WrongApiResponseException::class)
    fun addWeatherSampleFromApi()

    fun convertToDto(weatherSample: WeatherSample): WeatherSampleDto
    fun convertToEntity(weatherSampleDto: WeatherSampleDto): WeatherSample
}