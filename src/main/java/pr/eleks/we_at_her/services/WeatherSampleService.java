package pr.eleks.we_at_her.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.ApiWeatherSampleDto;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.repositories.WeatherSampleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WeatherSampleService {

    private WeatherSampleRepository weatherSampleRepository;
    private ObjectMapper mapper;
    private Environment env;

    public WeatherSampleService(WeatherSampleRepository weatherSampleRepository,ObjectMapper mapper, Environment env) {
        this.weatherSampleRepository = weatherSampleRepository;
        this.mapper = mapper;
        this.env = env;
    }


    public List<WeatherSample> getAllWeatherSamples() {
        List<WeatherSample> weatherSamples = new ArrayList<>();
        weatherSampleRepository.findAll().forEach(weatherSamples::add);
        Collections.reverse(weatherSamples);
        return weatherSamples;
    }

//    public WeatherSample findWeatherSample(Long id) {
//        return weatherSampleRepository.findById(id).orElse(null);
//    }

    public WeatherSample findFirstWeatherSampleByCityIdAndTime(int cityId, int time) {
        return weatherSampleRepository.findFirstByCityIdAndTime(cityId, time).orElse(null);
    }

    public void addWeatherSample(WeatherSample weatherSample) {
        weatherSampleRepository.save(weatherSample);
    }

//    public void updateWeatherSample(WeatherSample weatherSample) {
//        weatherSampleRepository.save(weatherSample);
//    }

//    public void deleteWeatherSample(Long id) {
//        weatherSampleRepository.deleteById(id);
//    }

    public WeatherSampleDto getWeatherSampleFromApi(String city, String lang, String units) {
        // Default values
        city = city.equals("") ? env.getProperty("OWApi.city") : city;
        lang = lang.equals("") ? env.getProperty("OWApi.lang") : lang;
        units = units.equals("") ? env.getProperty("OWApi.units") : units;

        // Prepare request string
//        String apiUrl = "http://api.openweathermap.org/data/2.5";
        String apiUrl = env.getProperty("OWApi.baseUrl","http://api.openweathermap.org/data/2.5");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("OWApi.request"))
                .queryParam("q", city)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("appid", env.getProperty("OWApi.key"));

        // Make request
        RestTemplate restTemplate = new RestTemplate();
        ApiWeatherSampleDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), ApiWeatherSampleDto.class);

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
                    apiResponseDto.getTime()
            );
        }
        return null;
    }

    public WeatherSampleDto addWeatherSampleFromApi() {
        WeatherSampleDto apiWeatherSampleDto = getWeatherSampleFromApi("", "", "");
        if (apiWeatherSampleDto != null) {
            WeatherSampleDto existingWeatherSampleDto = convertToDto(findFirstWeatherSampleByCityIdAndTime(
                    apiWeatherSampleDto.getCityId(),
                    apiWeatherSampleDto.getTime()
            ));
            if (existingWeatherSampleDto != null) {
                existingWeatherSampleDto.setId(null);
            }
            if (existingWeatherSampleDto == null || !existingWeatherSampleDto.equals(apiWeatherSampleDto)) {
                addWeatherSample(convertToEntity(apiWeatherSampleDto));
            }
            return apiWeatherSampleDto;
        } else {
            System.out.println("Error: weatherSampleService.getWeatherSampleFromApi() returned null response " +
                    "\n@WeatherSampleController:addWeatherSampleFromApi()");
            return null;
        }
    }

    private WeatherSampleDto convertToDto(WeatherSample weatherSample) {
        if (weatherSample == null) {
            return null;
        }
        return mapper.convertValue(weatherSample, WeatherSampleDto.class);
    }

    private WeatherSample convertToEntity(WeatherSampleDto weatherSampleDto) {
        if (weatherSampleDto == null) {
            return null;
        }
        return mapper.convertValue(weatherSampleDto, WeatherSample.class);
    }

//    @PostConstruct
//    public void init() {
//        weatherSampleRepository.saveAll(Arrays.asList(
//                new WeatherSample("Ternopil", -0.99f, -7.32f, 1030, 82, 5, 691650, 1579826046),
//                new WeatherSample("Ternopil", 1.67f, -5.64f, 985, 61, 51, 691650, 1579899329),
//                new WeatherSample("Ternopil", 0f, -6.78f, 985, 64, 51, 691650, 1579902899),
//                new WeatherSample("Ternopil", 0.56f, -5.25f, 985, 63, 51, 691650, 1579903776),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580132973),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580133658),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580134637),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580134637),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580137114),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580143089),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580143552),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580144981),
//                new WeatherSample("Ternopil", 2.78f, -4.05f, 969, 76, 50, 691650, 1580322598),
//                new WeatherSample("Ternopil", 3.33f, 0.28f, 969, 74, 50, 691650, 1580324738),
//                new WeatherSample("Ternopil", 3.89f, -0.82f, 975, 79, 78, 691650, 1580372020),
//                new WeatherSample("Ternopil", 3.89f, 0.4f, 975, 78, 78, 691650, 1580373254),
//                new WeatherSample("Ternopil", 3.89f, -0.85f, 975, 78, 78, 691650, 1580374202)
//                )
//        );
//    }
}
