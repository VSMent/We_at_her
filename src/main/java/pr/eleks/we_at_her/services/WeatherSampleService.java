package pr.eleks.we_at_her.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.annotations.OrderBy;
import org.springframework.core.annotation.Order;
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
    private Dotenv dotenv;
    private ObjectMapper mapper;

    public WeatherSampleService(WeatherSampleRepository weatherSampleRepository, Dotenv dotenv, ObjectMapper mapper) {
        this.weatherSampleRepository = weatherSampleRepository;
        this.dotenv = dotenv;
        this.mapper = mapper;
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
                .queryParam("appid", dotenv.get("OPENWEATHERMAP_API"));

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
}
