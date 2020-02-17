package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pr.eleks.we_at_her.dto.DarkSkyApiDto;
import pr.eleks.we_at_her.dto.OpenWeatherApiDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

import java.util.Optional;

@Service
public class OpenWeatherApiServiceImpl extends AbstractApiServiceImpl {

    private Environment env;
    private RestTemplate restTemplate;

    public OpenWeatherApiServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "OWApi";
    }


    @Override
    public WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units)
            throws PropertyNotFoundException, WrongApiResponseException {
        super.prepareParameters(latitude, longitude, lang, units);
        super.prepareBaseUrl();

        // Prepare request string
        uriBuilder
                .queryParam("lat", defaultValues.get("latitude"))
                .queryParam("lon", defaultValues.get("longitude"))
                .queryParam("lang", defaultValues.get("lang"))
                .queryParam("units", defaultValues.get("units"))
                .queryParam("appid",
                        Optional
                                .ofNullable(env.getProperty(apiPrefix + getName() + ".key"))
                                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".key")));

        // Make request
        OpenWeatherApiDto apiResponseDto =
                Optional
                        .ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), OpenWeatherApiDto.class))
                        .orElseThrow(() -> new WrongApiResponseException(DarkSkyApiDto.class.getName()));

        // return result
        return new WeatherSampleDto(
                apiResponseDto.getCityName(),
                apiResponseDto.getTemperature(),
                apiResponseDto.getFeelsLike(),
                apiResponseDto.getPressure(),
                apiResponseDto.getHumidity(),
                apiResponseDto.getClouds(),
                apiResponseDto.getCityId(),
                apiResponseDto.getTime(),
                apiResponseDto.getLatitude(),
                apiResponseDto.getLongitude()
        );
    }
}
