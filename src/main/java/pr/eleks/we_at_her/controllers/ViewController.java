package pr.eleks.we_at_her.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.services.ViewService;
import pr.eleks.we_at_her.services.WeatherSampleService;

import java.util.List;

@Controller
public class ViewController {


    private ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<WeatherSampleDto> weatherSampleDtoList = viewService.getAllWeatherSamples();
        model.addAttribute("weatherSampleDtoList", weatherSampleDtoList);
        return "index";
    }

}
