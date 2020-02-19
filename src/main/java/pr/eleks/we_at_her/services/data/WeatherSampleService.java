package pr.eleks.we_at_her.services.data;

import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

import java.util.ArrayList;
import java.util.List;

public interface WeatherSampleService {
    List<WeatherSample> getAllWeatherSamples();

    WeatherSample findFirstWeatherSampleByCityIdAndTime(int cityId, int time);

    void addWeatherSample(WeatherSample weatherSample);

    WeatherSampleDto getAverageFromWeatherSamples(ArrayList<WeatherSampleDto> apiDtos);

    void addWeatherSampleFromApi() throws PropertyNotFoundException, UnknownServiceNameException, WrongApiResponseException;

    WeatherSampleDto convertToDto(WeatherSample weatherSample);

    WeatherSample convertToEntity(WeatherSampleDto weatherSampleDto);
}
