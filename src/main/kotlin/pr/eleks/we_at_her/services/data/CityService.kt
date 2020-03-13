package pr.eleks.we_at_her.services.data

import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.entities.City

interface CityService {
    fun findAllCities(): MutableList<CityDto>
    fun convertToDto(city: City): CityDto
    fun convertToEntity(cityDto: CityDto): City
}