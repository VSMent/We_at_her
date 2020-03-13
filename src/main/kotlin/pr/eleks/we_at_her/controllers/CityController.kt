package pr.eleks.we_at_her.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.services.data.CityService

@RestController
@RequestMapping("/REST")
class CityController(
        private val cityService: CityService
) {
    @GetMapping("/city")
    fun getCities(): MutableList<CityDto> = cityService.findAllCities()
}