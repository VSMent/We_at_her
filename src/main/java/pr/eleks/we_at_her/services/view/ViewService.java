package pr.eleks.we_at_her.services.view;

import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.dto.UserDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ViewService {
    List<WeatherSampleDto> getAllWeatherSamples() throws PropertyNotFoundException;

    List<CityDto> getAllCities() throws PropertyNotFoundException;

    UserDto createUser(UserDto userDto) throws PropertyNotFoundException;
}
