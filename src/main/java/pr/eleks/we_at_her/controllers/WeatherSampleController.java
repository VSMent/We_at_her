package pr.eleks.we_at_her.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.services.WeatherSampleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherSampleController {

    private WeatherSampleService weatherSampleService;
    private ModelMapper modelMapper;

    public WeatherSampleController(WeatherSampleService weatherSampleService, ModelMapper modelMapper) {
        this.weatherSampleService = weatherSampleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/weatherSamples")
    public List<WeatherSampleDto> getAllWeatherSamples() {
        List<WeatherSample> weatherSamples = weatherSampleService.getAllWeatherSamples();
        return weatherSamples.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/weatherSamples/{id}")
    public WeatherSampleDto getWeatherSample(@PathVariable Long id) {
        return convertToDto(weatherSampleService.getWeatherSample(id));
    }

    @GetMapping("/weatherSamplesFromApi/{doReturn}")
    public WeatherSampleDto addWeatherSampleFromApi(@PathVariable boolean doReturn) {
        WeatherSampleDto apiWeatherSampleDto = weatherSampleService.getWeatherSampleFromApi("", "", "");
        addWeatherSample(apiWeatherSampleDto);
        return doReturn ? apiWeatherSampleDto : null;
    }

    @PostMapping("/weatherSamples")
    public void addWeatherSample(@RequestBody WeatherSampleDto weatherSampleDto) {
        weatherSampleService.addWeatherSample(convertToEntity(weatherSampleDto));
    }

    @PutMapping("/weatherSamples/{id}")
    public void updateWeatherSample(@RequestBody WeatherSampleDto weatherSampleDto) {
        weatherSampleService.updateWeatherSample(convertToEntity(weatherSampleDto));
    }

    @DeleteMapping("/weatherSamples/{id}")
    public void deleteWeatherSample(@PathVariable Long id) {
        weatherSampleService.deleteWeatherSample(id);
    }


    private WeatherSampleDto convertToDto(WeatherSample weatherSample) {
        return modelMapper.map(weatherSample, WeatherSampleDto.class);
    }

    private WeatherSample convertToEntity(WeatherSampleDto weatherSampleDto) {
        return modelMapper.map(weatherSampleDto, WeatherSample.class);
    }
//    INSERT INTO `weather_sample` (`id`, `city_id`, `city_name`, `clouds`, `feels_like`, `humidity`, `pressure`, `temperature`, `time`) VALUES ('1', '691650', 'Ternopil', '5', '-7.32', '82', '1030', '-0.99', '1579826046')
}
