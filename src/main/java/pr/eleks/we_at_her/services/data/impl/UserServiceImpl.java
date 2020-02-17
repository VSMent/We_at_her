package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private ObjectMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public void createUser(UserDto userDto) {
        userRepository.save(convertToEntity(userDto));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return convertToDto(user);
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
}
