package pr.eleks.we_at_her.services.api.impl;

        import org.springframework.core.env.Environment;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;
        import org.springframework.web.util.UriComponentsBuilder;
        import pr.eleks.we_at_her.dto.OpenWeatherApiDto;
        import pr.eleks.we_at_her.dto.WeatherSampleDto;
        import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
        import pr.eleks.we_at_her.services.api.ApiService;

@Service
public class OpenWeatherApiServiceImpl implements ApiService {

    private Environment env;
    private RestTemplate restTemplate;

    public OpenWeatherApiServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherSampleDto getWeatherSampleFrmApi(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("OWApi.lang") : lang;
        units = units.equals("") ? env.getProperty("OWApi.units") : units;

        // Prepare request string
        String apiUrl = env.getProperty("OWApi.baseUrl");
        if (apiUrl == null) {
            throw new PropertyNotFoundException("OWApi.baseUrl");
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("OWApi.request"))
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("appid", env.getProperty("OWApi.key"));

        // Make request
        OpenWeatherApiDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), OpenWeatherApiDto.class);

        // Handle error, return result
        if (apiResponseDto != null) {
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
        return null;
    }
}
