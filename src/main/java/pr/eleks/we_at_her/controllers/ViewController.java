package pr.eleks.we_at_her.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.services.view.ViewService;

import java.util.List;

@Controller
public class ViewController {


    private ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) throws PropertyNotFoundException {
        List<WeatherSampleDto> weatherSampleDtoList = viewService.getAllWeatherSamples();
        model.addAttribute("weatherSampleDtoList", weatherSampleDtoList);
        return "index";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) throws PropertyNotFoundException {
        List<CityDto> cities = viewService.getAllCities();
        model.addAttribute("cities", cities);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(UserDto userDto) throws PropertyNotFoundException {
//        try {
        viewService.createUser(userDto);
//        }catch (CreateUserException ex){
//            return "redirect:/login";
//        }
        return "redirect:/";
    }

    @GetMapping("/blog")
    public String showBlogPage(Model model) throws PropertyNotFoundException {
        List<BlogPostDto> blogPostDtoList = viewService.getAllBlogPosts();
        model.addAttribute("blogPostDtoList", blogPostDtoList);
        return "blog";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam("u") String userUuid) throws PropertyNotFoundException {
        viewService.activateUser(userUuid);
        return "redirect:/";
    }


}
