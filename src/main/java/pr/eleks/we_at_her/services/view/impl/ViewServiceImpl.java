package pr.eleks.we_at_her.services.view.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.WeatherSampleDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ViewServiceImpl {

    private Environment env;
    private RestTemplate restTemplate;

    public ViewServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    public List<WeatherSampleDto> getAllWeatherSamples() {
        String apiUrl = env.getProperty("server.host") + ":" + env.getProperty("server.port");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment("weatherSamplesREST");

        // Make request
        WeatherSampleDto[] responseDtos = restTemplate.getForObject(uriBuilder.toUriString(), WeatherSampleDto[].class);
        List<WeatherSampleDto> weatherSampleDtos;
        if (responseDtos != null) {
            weatherSampleDtos = new ArrayList<>(Arrays.asList(responseDtos));
            return weatherSampleDtos;
        } else {
            return new ArrayList<>();
        }
    }
}
