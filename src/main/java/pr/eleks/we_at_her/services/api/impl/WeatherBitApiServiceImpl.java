package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.DarkSkyApiDto;
import pr.eleks.we_at_her.dto.WeatherBitApiDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

import java.util.Optional;

@Service
public class WeatherBitApiServiceImpl extends AbstractApiServiceImpl {
    private Environment env;
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
    public WeatherSampleDto getWeatherSampleFromApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException, WrongApiResponseException {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("wApis.WBApi.lang") : lang;
        units = units.equals("") ? env.getProperty("wApis.WBApi.units") : units;

        // Prepare request string
        String apiUrl = env.getProperty("wApis.WBApi.baseUrl");
        if (apiUrl == null) {
            throw new PropertyNotFoundException("wApis.WBApi.baseUrl");
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("wApis.WBApi.request"))
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("key", env.getProperty("wApis.WBApi.key"));

        // Make request
//        WeatherBitApiDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), WeatherBitApiDto.class);
        WeatherBitApiDto apiResponseDto =
                Optional
                        .ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), WeatherBitApiDto.class))
//                        .filter(obj -> obj.getClass().equals(WeatherBitApiDto.class))
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
