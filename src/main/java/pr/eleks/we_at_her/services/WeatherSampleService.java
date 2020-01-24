package pr.eleks.we_at_her.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        city = city.equals("") ? "Ternopil" : city;
        lang = lang.equals("") ? "UA" : lang;
        units = units.equals("") ? "metric" : units;
        final String uri = String.format("http://api.openweathermap.org/data/2.5/weather" +
                        "?q=%s" +
                        "&lang=%s" +
                        "&units=%s" +
                        "&appid=%s",
                city, lang, units, dotenv.get("OPENWEATHERMAP_API")
        );
        RestTemplate restTemplate = new RestTemplate();
        ApiWeatherSampleDto apiResponse = restTemplate.getForObject(uri, ApiWeatherSampleDto.class);
        assert apiResponse != null;
        return new WeatherSampleDto(
                apiResponse.getName(),
                apiResponse.getMain().getTemp(),
                apiResponse.getMain().getFeels_like(),
                apiResponse.getMain().getPressure(),
                apiResponse.getMain().getHumidity(),
                apiResponse.getClouds().getAll(),
                apiResponse.getId(),
                apiResponse.getDt()
        );
    }


    @PostConstruct
    public void init() {
        weatherSampleRepository.saveAll(Arrays.asList(
                new WeatherSample(1L, "Ternopil", -0.99f, -7.32f, 1030,
                        82, 5, 691650, 1579826046),
                new WeatherSample("Ternopil", -0.99f, -7.32f, 1030,
                        82, 5, 691650, 1579829302)
                )
        );
//        getWeatherSampleFromApi("","","");
    }
}
