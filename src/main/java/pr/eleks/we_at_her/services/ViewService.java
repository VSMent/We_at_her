package pr.eleks.we_at_her.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.repositories.WeatherSampleRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ViewService {


    private WeatherSampleRepository weatherSampleRepository;
    private ObjectMapper mapper;
    private Environment env;
    private RestTemplate restTemplate;

    public ViewService(WeatherSampleRepository weatherSampleRepository, ObjectMapper mapper, Environment env, RestTemplate restTemplate) {
        this.weatherSampleRepository = weatherSampleRepository;
        this.mapper = mapper;
        this.env = env;
        this.restTemplate = restTemplate;
    }

    public List<WeatherSampleDto> getAllWeatherSamples() {
        String apiUrl = env.getProperty("server.host") +":"+ env.getProperty("server.port");
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
