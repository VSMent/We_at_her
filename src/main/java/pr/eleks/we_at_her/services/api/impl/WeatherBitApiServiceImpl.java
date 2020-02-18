package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pr.eleks.we_at_her.dto.weather.WeatherBitApiDto;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

import java.util.Optional;

@Service
public class WeatherBitApiServiceImpl extends AbstractApiServiceImpl {

    private RestTemplate restTemplate;

    public WeatherBitApiServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "WBApi";
    }

    @Override
    public WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units)
            throws PropertyNotFoundException, WrongApiResponseException {
        prepareParameters(latitude, longitude, lang, units);
        prepareBaseUrl();

        // Prepare request string
        uriBuilder
                .queryParam("lat", defaultValues.get("latitude"))
                .queryParam("lon", defaultValues.get("longitude"))
                .queryParam("lang", defaultValues.get("lang"))
                .queryParam("units", defaultValues.get("units"))
                .queryParam("key",
                        Optional
                                .ofNullable(env.getProperty(apiPrefix + getName() + ".key"))
                                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".key")));

        // Make request
        WeatherBitApiDto apiResponseDto =
                Optional
                        .ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), WeatherBitApiDto.class))
                        .orElseThrow(() -> new WrongApiResponseException(WeatherBitApiDto.class.getName()));

        // return result
        return new WeatherSampleDto(
                apiResponseDto.getCityName(),
                apiResponseDto.getTemperature(),
                apiResponseDto.getFeelsLike(),
                apiResponseDto.getPressure(),
                apiResponseDto.getHumidity(),
                apiResponseDto.getClouds(),
                -1,
                apiResponseDto.getTime(),
                apiResponseDto.getLatitude(),
                apiResponseDto.getLongitude()
        );
    }
}
