package pr.eleks.we_at_her.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.services.data.impl.WeatherSampleServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/REST")
public class WeatherSampleController {

    private WeatherSampleServiceImpl weatherServiceImpl;
    private ObjectMapper mapper;

    public WeatherSampleController(WeatherSampleServiceImpl weatherServiceImpl, ObjectMapper mapper) {
        this.weatherServiceImpl = weatherServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping("/weatherSample")
    public List<WeatherSampleDto> getAllWeatherSamples() {
        List<WeatherSample> weatherSamples = weatherServiceImpl.getAllWeatherSamples();
        return weatherSamples.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
//
//    @GetMapping("/weatherSample/{id}")
//    public WeatherSampleDto getWeatherSample(@PathVariable Long id) {
//        return convertToDto(weatherSampleService.findWeatherSample(id));
//    }
//
//    @PostMapping("/weatherSample")
//    public void addWeatherSample(@RequestBody WeatherSampleDto weatherSampleDto) {
//        weatherSampleService.addWeatherSample(convertToEntity(weatherSampleDto));
//    }
//
//    @PutMapping("/weatherSample/{id}")
//    public void updateWeatherSample(@RequestBody WeatherSampleDto weatherSampleDto) {
//        weatherSampleService.updateWeatherSample(convertToEntity(weatherSampleDto));
//    }
//
//    @DeleteMapping("/weatherSample/{id}")
//    public void deleteWeatherSample(@PathVariable Long id) {
//        weatherSampleService.deleteWeatherSample(id);
//    }


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
//    INSERT INTO `weather_sample` (`id`, `city_id`, `city_name`, `clouds`, `feels_like`, `humidity`, `pressure`, `temperature`, `time`) VALUES ('1', '691650', 'Ternopil', '5', '-7.32', '82', '1030', '-0.99', '1579826046')
}
