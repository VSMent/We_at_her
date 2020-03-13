package pr.eleks.we_at_her.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.services.data.WeatherSampleService
import pr.eleks.we_at_her.services.data.impl.WeatherSampleServiceImpl
import pr.eleks.we_at_her.services.view.ViewService

@Controller
class ViewController(
        private val viewService: ViewService,
        private val weatherSampleService: WeatherSampleService
) {
    @GetMapping("/")
    @Throws(PropertyNotFoundException::class)
    fun viewHomePage(model: Model): String? {
        val weatherSampleDtoList = viewService.getAllWeatherSamples()
        model.addAttribute("weatherSampleDtoList", weatherSampleDtoList)
        return "index"
    }

    @GetMapping("/register")
    @Throws(PropertyNotFoundException::class)
    fun viewRegisterPage(model: Model): String? {
        model["cities"] = viewService.getAllCities()
        return "register"
    }

    @PostMapping("/register")
    @Throws(PropertyNotFoundException::class)
    fun addUser(userDto: UserDto): String {
        viewService.createUser(userDto)
        return "redirect:/"
    }

    @GetMapping("/blog")
    @Throws(PropertyNotFoundException::class)
    fun showBlogPage(model: Model): String {
        model["blogPostDtoList"] = viewService.getAllBlogPosts()
        return "blog"
    }

    @PostMapping("/blog")
    @Throws(PropertyNotFoundException::class)
    fun createNewBlogPost(blogPostDto: BlogPostDto): String {
        viewService.createNewBlogPost(blogPostDto)
        return "redirect:/blog"
    }


    @GetMapping("/activate")
    @Throws(PropertyNotFoundException::class)
    fun activateUser(@RequestParam("u") userUuid: String?): String {
        if (userUuid != null) {
            viewService.activateUser(userUuid)
        }
        return "redirect:/"
    }
}