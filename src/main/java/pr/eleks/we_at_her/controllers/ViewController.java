package pr.eleks.we_at_her.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.services.view.impl.ViewServiceImpl;

import java.util.List;

@Controller
public class ViewController {


    private ViewServiceImpl viewServiceImpl;

    public ViewController(ViewServiceImpl viewServiceImpl) {
        this.viewServiceImpl = viewServiceImpl;
    }


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<WeatherSampleDto> weatherSampleDtoList = viewServiceImpl.getAllWeatherSamples();
        model.addAttribute("weatherSampleDtoList", weatherSampleDtoList);
        return "index";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@RequestBody UserDto userDto) {
//        try {
        viewServiceImpl.createUser(userDto);
//        }catch (CreateUserException ex){
//            return "redirect:/login";
//        }
        return "redirect:/";
    }

}
