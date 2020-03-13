package pr.eleks.we_at_her.services.data.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.core.env.Environment
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto
import pr.eleks.we_at_her.entities.WeatherSample
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException
import pr.eleks.we_at_her.exceptions.WrongApiResponseException
import pr.eleks.we_at_her.repositories.WeatherSampleRepository
import pr.eleks.we_at_her.services.api.ApiServiceFactory
import pr.eleks.we_at_her.services.data.WeatherSampleService
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Service
class WeatherSampleServiceImpl(
        private val weatherSampleRepository: WeatherSampleRepository,
        private val mapper: ObjectMapper,
        private val env: Environment
) : WeatherSampleService {

    override fun getAllWeatherSamples(): List<WeatherSample> = weatherSampleRepository.findAll().toList().reversed()

    override fun findFirstWeatherSampleByCityIdAndTime(cityId: Int, time: Int): WeatherSample? =
            weatherSampleRepository.findFirstByCityIdAndTime(cityId, time)

    override fun addWeatherSample(weatherSample: WeatherSample): WeatherSample =
            weatherSampleRepository.save(weatherSample)

    override fun getAverageFromWeatherSamples(apiDtos: List<WeatherSampleDto>): WeatherSampleDto {
        when (apiDtos.size) {
            0 -> return WeatherSampleDto()
            1 -> return apiDtos.first()
            else -> {
                val averageDto = apiDtos.first()
                val size = apiDtos.size
                apiDtos
                        .drop(1)
                        .stream()
                        .map {
                            // add all
                            averageDto.temperature =
                                    it.temperature?.let { itParam -> averageDto.temperature?.plus(itParam) }
                            averageDto.feelsLike =
                                    it.feelsLike?.let { itParam -> averageDto.feelsLike?.plus(itParam) }
                            averageDto.pressure =
                                    it.pressure?.let { itParam -> averageDto.pressure?.plus(itParam) }
                            averageDto.humidity =
                                    it.humidity?.let { itParam -> averageDto.humidity?.plus(itParam) }
                            averageDto.clouds =
                                    it.clouds?.let { itParam -> averageDto.clouds?.plus(itParam) }

                            averageDto.cityName = if (averageDto.cityName == null) it.cityName else null
                            averageDto.cityId = if (averageDto.cityId == -1) it.cityId else -1
                            averageDto.time = if (it.time!! < averageDto.time!!) it.time else averageDto.time
                        }
                        .let {
                            // Divide all
                            averageDto.temperature =
                                    averageDto.temperature?.times(100)?.div(size)?.roundToInt()?.div(100f)
                            averageDto.feelsLike =
                                    averageDto.feelsLike?.times(100)?.div(size)?.roundToInt()?.div(100f)
                            averageDto.pressure =
                                    averageDto.pressure?.times(100)?.div(size)?.roundToInt()?.div(100f)

                            averageDto.humidity = averageDto.humidity?.div(size.toFloat())?.roundToInt()
                            averageDto.clouds = averageDto.clouds?.div(size.toFloat())?.roundToInt()
                        }
                return averageDto
            }
        }
    }

//    @Scheduled(cron = "0 0 */1 * * *")
//    @Scheduled(cron = "*/30 * * * * *")
    @Throws(PropertyNotFoundException::class, UnknownServiceNameException::class, WrongApiResponseException::class)
    override fun addWeatherSampleFromApi() {
        println("${SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date())}\t\tExecuting \"addWeatherSampleFromApi\"")
        val averageDto = getAverageFromWeatherSamples((
                env.getProperty("wApis.list", Array<String>::class.java)
                        ?: throw PropertyNotFoundException("wApis.list")
                )
                .map {
                    ApiServiceFactory.getService(it).getWeatherSampleFromApi("", "", "", "")
                }
        )

        if (averageDto.cityId != null && averageDto.time != null) {
            val existingDto =
                    findFirstWeatherSampleByCityIdAndTime(averageDto.cityId!!, averageDto.time!!)
                            ?.let { convertToDto(it) }
            if (averageDto != existingDto?.apply { id=null }){
                addWeatherSample(convertToEntity(averageDto))
            }
        }
    }

    override fun convertToDto(weatherSample: WeatherSample): WeatherSampleDto = mapper.convertValue(weatherSample)
    override fun convertToEntity(weatherSampleDto: WeatherSampleDto): WeatherSample =
            mapper.convertValue(weatherSampleDto)
}