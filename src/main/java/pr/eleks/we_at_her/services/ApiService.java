package pr.eleks.we_at_her.services;

import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;

public interface ApiService {
    WeatherSampleDto getWeatherSampleFrmApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException;
}
