package pr.eleks.we_at_her.services.api;

import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;

public interface ApiService {
    WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException;
}
