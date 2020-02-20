package pr.eleks.we_at_her.services.view.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.services.EmailService;
import pr.eleks.we_at_her.services.view.ViewService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ViewServiceImpl implements ViewService {

    private Environment env;
    private RestTemplate restTemplate;
    private EmailService emailService;

    public ViewServiceImpl(Environment env, RestTemplate restTemplate, EmailService emailService) {
        this.env = env;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    @Override
    public UriComponentsBuilder hostUriBuilder() throws PropertyNotFoundException {
        return UriComponentsBuilder
                .fromUriString(
                        Optional
                                .ofNullable(env.getProperty("server.host"))
                                .orElseThrow(() -> new PropertyNotFoundException("server.host"))
                                .concat(":").concat(
                                Optional
                                        .ofNullable(env.getProperty("server.port"))
                                        .orElseThrow(() -> new PropertyNotFoundException("server.port"))
                        )
                );
    }

    @Override
    public List<WeatherSampleDto> getAllWeatherSamples() throws PropertyNotFoundException {
        String requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("weatherSample")
                .toUriString();

        // Make request
        return new ArrayList<>(Arrays.asList(
                Optional
                        .ofNullable(restTemplate.getForObject(requestUri, WeatherSampleDto[].class))
                        .orElse(new WeatherSampleDto[0])
        ));
    }

    @Override
    public List<CityDto> getAllCities() throws PropertyNotFoundException {
        String requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("city")
                .toUriString();

        // Make request
        CityDto[] cityDtos = restTemplate.getForObject(requestUri, CityDto[].class);
        return new ArrayList<>(Arrays.asList(
                Optional
                        .ofNullable(cityDtos)
                        .orElse(new CityDto[0])
        ));
    }

    @Override
    public UserDto createUser(UserDto userDto) throws PropertyNotFoundException {
        String requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("user")
                .toUriString();

        UserDto savedUser = restTemplate.postForEntity(requestUri, userDto, UserDto.class).getBody();

        if (savedUser != null) {
            String activateUri = hostUriBuilder()
                    .pathSegment("activate")
                    .queryParam("u", savedUser.getUuid().toString())
                    .toUriString();

            emailService.sendEmail(savedUser.getEmail(),
                    "Your account was registered",
                    "Dear " + savedUser.getUsername() + "." +
                            "\nYour account was successfully created." +
                            "\nTo activate account please go to " + activateUri + " .");
        }
        return savedUser;
    }

    @Override
    public UserDto activateUser(String userUuid) throws PropertyNotFoundException {
        String requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("activateUser")
                .pathSegment(userUuid)
                .toUriString();

        return Optional
                .ofNullable(restTemplate.getForObject(requestUri, UserDto.class))
                .orElse(null);
    }

    @Override
    public List<BlogPostDto> getAllBlogPosts() throws PropertyNotFoundException {
        String requestUri = hostUriBuilder()
                .pathSegment("REST")
                .pathSegment("user")
                .toUriString();

        // Make request
        BlogPostDto[] blogPostDto = restTemplate.getForObject(requestUri, BlogPostDto[].class);
        return new ArrayList<>(Arrays.asList(
                Optional
                        .ofNullable(blogPostDto)
                        .orElse(new BlogPostDto[0])
        ));
    }
}
