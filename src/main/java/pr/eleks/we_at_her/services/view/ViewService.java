package pr.eleks.we_at_her.services.view;

import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;

import java.util.List;

public interface ViewService {
    UriComponentsBuilder hostUriBuilder() throws PropertyNotFoundException;

    List<WeatherSampleDto> getAllWeatherSamples() throws PropertyNotFoundException;

    List<CityDto> getAllCities() throws PropertyNotFoundException;

    UserDto createUser(UserDto userDto) throws PropertyNotFoundException;

    List<BlogPostDto> getAllBlogPosts() throws PropertyNotFoundException;

    UserDto activateUser(String userUuid) throws PropertyNotFoundException;
}
