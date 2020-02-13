package pr.eleks.we_at_her.services.api.impl;

import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;
import pr.eleks.we_at_her.services.api.ApiService;

public abstract class AbstractApiServiceImpl implements ApiService {

    public AbstractApiServiceImpl() {
    }

    @Override
    public WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException, WrongApiResponseException {
        return null;
    }
}
