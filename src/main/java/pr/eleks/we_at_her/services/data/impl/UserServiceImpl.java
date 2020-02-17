package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.repositories.UserRepository;
import pr.eleks.we_at_her.services.data.UserService;

import javax.annotation.PostConstruct;

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


    public void createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(convertToEntity(userDto));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
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
//        userRepository.save(convertToEntity(new UserDto("admin", "$2y$12$lfMgScUaJXeSvNHysXAi6uqqoIQkV0XjCf0LQPx21muVJoD7sfWpS", "Ternopil")));
//    }
}
