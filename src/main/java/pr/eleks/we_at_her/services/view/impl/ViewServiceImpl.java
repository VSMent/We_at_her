package pr.eleks.we_at_her.services.view.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.services.view.ViewService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ViewServiceImpl implements ViewService {

    private Environment env;
    private RestTemplate restTemplate;

    public ViewServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<WeatherSampleDto> getAllWeatherSamples() throws PropertyNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(
                        Optional.ofNullable(env.getProperty("server.host"))
                                .orElseThrow(() -> new PropertyNotFoundException("server.host"))
                                .concat(":").concat(
                                Optional.ofNullable(env.getProperty("server.port"))
                                        .orElseThrow(() -> new PropertyNotFoundException("server.port"))
                        )
                )
                .pathSegment("weatherSampleREST");

        // Make request

        return new ArrayList<>(Arrays.asList(
                Optional
                        .ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), WeatherSampleDto[].class))
                        .orElse(new WeatherSampleDto[0])
        ));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        String apiUrl = env.getProperty("server.host") + ":" + env.getProperty("server.port");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment("userREST");

        return restTemplate.postForEntity(uriBuilder.toUriString(), userDto, UserDto.class).getBody();
    }
}
