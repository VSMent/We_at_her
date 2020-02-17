package pr.eleks.we_at_her.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.services.data.impl.UserServiceImpl;

@RestController
public class UserController {
    private UserServiceImpl userService;
    private ObjectMapper mapper;

    public UserController(UserServiceImpl userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/userREST")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping("/userREST")
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
