package pr.eleks.we_at_her.services.data.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.entities.City
import pr.eleks.we_at_her.repositories.CityRepository
import pr.eleks.we_at_her.services.data.CityService
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.StreamSupport
import javax.annotation.PostConstruct

@Service
class CityServiceImpl(
        private val mapper: ObjectMapper,
        private val cityRepository: CityRepository
) : CityService {
    override fun findAllCities(): MutableList<CityDto> =
            StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                    .map { city: City -> convertToDto(city) }.collect(Collectors.toList())

    override fun convertToDto(city: City): CityDto = mapper.convertValue(city)
    override fun convertToEntity(cityDto: CityDto): City = mapper.convertValue(cityDto)

//    @PostConstruct
//    @Throws(IOException::class)
//    fun init() {
//        Files.lines(Paths.get("src/main/resources/public/data/main_cities_ua.json")).use { s ->
//            val citiesJson: String = s.collect(Collectors.joining())
//            val cityDtos: List<CityDto> = mapper.readValue(citiesJson)
//            cityDtos.stream().map(::convertToEntity).forEach { entity -> cityRepository.save(entity) }
//        }
//    }
}