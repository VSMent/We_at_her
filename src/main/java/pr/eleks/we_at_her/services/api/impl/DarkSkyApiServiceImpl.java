package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.DarkSkyApiDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;

@Service
public class DarkSkyApiServiceImpl extends AbstractApiServiceImpl {

    private Environment env;
    private RestTemplate restTemplate;

    public DarkSkyApiServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "DSApi";
    }

    @Override
    public WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("wApis.DSApi.lang") : lang;
        units = units.equals("") ? env.getProperty("wApis.DSApi.units") : units;

        // Prepare request string
        String apiUrl = env.getProperty("wApis.DSApi.baseUrl");
        if (apiUrl == null) {
            throw new PropertyNotFoundException("wApis.DSApi.baseUrl");
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("wApis.DSApi.request"))
                .pathSegment(env.getProperty("wApis.DSApi.key"))
                .pathSegment(latitude + "," + longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("exclude", env.getProperty("wApis.DSApi.exclude"));

        // Make request
        DarkSkyApiDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), DarkSkyApiDto.class);

        // Handle error, return result
        if (apiResponseDto != null) {
            return new WeatherSampleDto(
                    null,
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
        return null;
    }
}
