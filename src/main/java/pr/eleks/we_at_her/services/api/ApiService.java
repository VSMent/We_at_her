package pr.eleks.we_at_her.services.api;

import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

public interface ApiService {
    WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException, WrongApiResponseException;

    String getName();

    void prepareParameters(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException;

    void prepareBaseUrl() throws PropertyNotFoundException;
}
