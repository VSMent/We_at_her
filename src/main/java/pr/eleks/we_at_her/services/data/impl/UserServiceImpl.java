package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.RoleDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.repositories.UserRepository;
import pr.eleks.we_at_her.services.data.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private ObjectMapper mapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto createUser(UserDto userDto) {
        userDto.setRole(RoleDto.USER);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUuid(
                UUID.nameUUIDFromBytes(
                        (userDto.getUsername() + userDto.getEmail()).getBytes(StandardCharsets.UTF_8)
                )
        );
        return convertToDto(userRepository.save(convertToEntity(userDto)));
    }

    @Override
    public UserDto findUserByUuid(String uuidString) {
        return userRepository.findByUuid(UUID.fromString(uuidString))
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDto activateUserByUuid(String uuidString) {
        UserDto existingUser = findUserByUuid(uuidString);
        existingUser.setActivated(true);
        return Optional
                .of(userRepository.save(convertToEntity(existingUser)))
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }
        return mapper.convertValue(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return mapper.convertValue(userDto, User.class);
    }

//    @PostConstruct
//    private void init() {
//        userRepository.save(convertToEntity(new UserDto(
//                "admin",
//                "$2a$12$SZPBnZ/DXjSyVyMCypxjgO2rgA/UAQqJ45krkWCC6AR4BNUXh4WZW",
//                691650L,
//                "ad@m.in",
//                RoleDto.ADMIN,
//                true,
//                UUID.nameUUIDFromBytes("adminad@m.in".getBytes(StandardCharsets.UTF_8)))
//        ));
//    }
}
