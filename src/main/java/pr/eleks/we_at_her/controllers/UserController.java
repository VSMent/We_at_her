package pr.eleks.we_at_her.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.services.data.UserService;

@RestController
@RequestMapping("/REST")
public class UserController {
    private UserService userService;
    private ObjectMapper mapper;

    public UserController(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping("/user")
    public String getUsers() {
        return "get";
    }

    private UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }
        return mapper.convertValue(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return mapper.convertValue(userDto, User.class);
    }
}
