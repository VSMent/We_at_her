package pr.eleks.we_at_her.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pr.eleks.we_at_her.dto.WeatherSampleDto;

import java.util.List;

@Controller
public class AppController {

    private WeatherSampleController weatherSampleController;

    public AppController(WeatherSampleController weatherSampleController) {
        this.weatherSampleController = weatherSampleController;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<WeatherSampleDto> weatherSampleDtoList = weatherSampleController.getAllWeatherSamples();
        model.addAttribute("weatherSampleDtoList",weatherSampleDtoList);
        return "index";
    }
}
