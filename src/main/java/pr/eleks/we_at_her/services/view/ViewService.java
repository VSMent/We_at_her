package pr.eleks.we_at_her.services.view;

import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;

import java.util.List;

public interface ViewService {
    List<WeatherSampleDto> getAllWeatherSamples();
}
