package pr.eleks.we_at_her.controllers

import org.springframework.web.bind.annotation.*
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.services.data.UserService

@RestController
@RequestMapping("/REST")
class UserController(
        private val userService: UserService
) {
    @GetMapping("/activateUser/{uuidString}")
    fun activateUserByUuid(@PathVariable uuidString: String): UserDto? = userService.activateUserByUuid(uuidString)

    @PostMapping("/user")
    fun createUser(@RequestBody userDto: UserDto): UserDto? = userService.createUser(userDto)
}