package pr.eleks.we_at_her.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.ApiWeatherSampleDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.repositories.WeatherSampleRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherSampleService {

    private WeatherSampleRepository weatherSampleRepository;
    private Dotenv dotenv;

    public WeatherSampleService(WeatherSampleRepository weatherSampleRepository, Dotenv dotenv) {
        this.weatherSampleRepository = weatherSampleRepository;
        this.dotenv = dotenv;
    }


    public List<WeatherSample> getAllWeatherSamples() {
        List<WeatherSample> weatherSamples = new ArrayList<>();
        weatherSampleRepository.findAll().forEach(weatherSamples::add);
        return weatherSamples;
    }

    public WeatherSample getWeatherSample(Long id) {
        return weatherSampleRepository.findById(id).orElse(null);
    }

    public void addWeatherSample(WeatherSample weatherSample) {
        weatherSampleRepository.save(weatherSample);
    }

    public void updateWeatherSample(WeatherSample weatherSample) {
        weatherSampleRepository.save(weatherSample);
    }

    public void deleteWeatherSample(Long id) {
        weatherSampleRepository.deleteById(id);
    }

    public WeatherSampleDto getWeatherSampleFromApi(String city, String lang, String units) {
        // Default values
        city = city.equals("") ? "Ternopil" : city;
        lang = lang.equals("") ? "UA" : lang;
        units = units.equals("") ? "metric" : units;

        // Prepare request string
        String apiUrl = "http://api.openweathermap.org/data/2.5";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment("weather")
                .queryParam("q", city)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("appid",dotenv.get("OPENWEATHERMAP_API"));

        // Make request
        RestTemplate restTemplate = new RestTemplate();
        ApiWeatherSampleDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), ApiWeatherSampleDto.class);

        // Handle error, return result
        if (apiResponseDto != null) {
            return new WeatherSampleDto(
                    apiResponseDto.getName(),
                    apiResponseDto.getMain().getTemp(),
                    apiResponseDto.getMain().getFeels_like(),
                    apiResponseDto.getMain().getPressure(),
                    apiResponseDto.getMain().getHumidity(),
                    apiResponseDto.getClouds().getAll(),
                    apiResponseDto.getId(),
                    apiResponseDto.getDt()
            );
        }
        return null;
    }


//    @PostConstruct
//    public void init() {
//        weatherSampleRepository.saveAll(Arrays.asList(
//                new WeatherSample(1L, "Ternopil", -0.99f, -7.32f, 1030,
//                        82, 5, 691650, 1579826046),
//                new WeatherSample("Ternopil", -0.99f, -7.32f, 1030,
//                        82, 5, 691650, 1579829302)
//                )
//        );
//        getWeatherSampleFromApi("","","");
//    }
}
