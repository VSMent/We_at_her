package pr.eleks.we_at_her.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.services.data.CityService;

import java.util.List;

@RestController
@RequestMapping("/REST")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public List<CityDto> getCities() {
        return cityService.findAllCities();
    }
}
