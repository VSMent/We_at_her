package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;
import pr.eleks.we_at_her.repositories.WeatherSampleRepository;
import pr.eleks.we_at_her.services.api.ApiServiceFactory;
import pr.eleks.we_at_her.services.data.WeatherService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherSampleRepository weatherSampleRepository;
    private ObjectMapper mapper;
    private Environment env;

    public WeatherServiceImpl(
            WeatherSampleRepository weatherSampleRepository,
            ObjectMapper mapper,
            Environment env
    ) {
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


    public WeatherSampleDto getAverageFromWeatherSamples(ArrayList<WeatherSampleDto> apiDtos) {
        WeatherSampleDto averageDto = new WeatherSampleDto();
        int weatherSamplesAmount = apiDtos.size();

        averageDto.setLatitude(apiDtos.get(0).getLatitude());
        averageDto.setLongitude(apiDtos.get(0).getLongitude());
        averageDto.setCityName(apiDtos.get(0).getCityName());
        averageDto.setTime(apiDtos.get(0).getTime());

        // add all
        for (WeatherSampleDto apiDto : apiDtos) {
            averageDto.setTemperature(
                    averageDto.getTemperature() + apiDto.getTemperature()
            );
            averageDto.setFeelsLike(
                    averageDto.getFeelsLike() + apiDto.getFeelsLike()
            );
            averageDto.setPressure(
                    averageDto.getPressure() + apiDto.getPressure()
            );
            averageDto.setHumidity(
                    averageDto.getHumidity() + apiDto.getHumidity()
            );
            averageDto.setClouds(
                    averageDto.getClouds() + apiDto.getClouds()
            );
            if (apiDto.getCityId() != -1) {
                averageDto.setCityId(apiDto.getCityId());
            }
            if (apiDto.getTime() < averageDto.getTime()) {
                averageDto.setTime(apiDto.getTime());
            }
        }

        // Divide all
        averageDto.setTemperature(
                Math.round(averageDto.getTemperature() / weatherSamplesAmount * 100) / 100.0f
        );
        averageDto.setFeelsLike(
                Math.round(averageDto.getFeelsLike() / weatherSamplesAmount * 100) / 100.0f
        );
        averageDto.setPressure(
                Math.round(averageDto.getPressure() / weatherSamplesAmount * 100) / 100.0f
        );
        averageDto.setHumidity(
                Math.round(averageDto.getHumidity() / (float) weatherSamplesAmount)
        );
        averageDto.setClouds(
                Math.round(averageDto.getClouds() / (float) weatherSamplesAmount)
        );

        return averageDto;
    }

    @Scheduled(cron = "0 0 */1 * * *")
//    @Scheduled(cron = "*/30 * * * * *")
    // second, minute, hour, day of month, month, day(s) of week (* any, */x every x, ? no specification)
    public void addWeatherSampleFromApi() throws PropertyNotFoundException, UnknownServiceNameException, WrongApiResponseException {
        System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss\t\t").format(new Date()) + "Executing \"addWeatherSampleFromApi\"");
        String[] apiNames = env.getProperty("wApis.list", String[].class);
        WeatherSampleDto averageDto = getAverageFromWeatherSamples(
                new ArrayList<>(
                        Arrays.asList(
                                ApiServiceFactory.getService(apiNames[0]).getWeatherSampleFromApi("", "", "", ""),
                                ApiServiceFactory.getService(apiNames[1]).getWeatherSampleFromApi("", "", "", ""),
                                ApiServiceFactory.getService(apiNames[2]).getWeatherSampleFromApi("", "", "", "")
                        )
                )
        );

        WeatherSampleDto existingWeatherSampleDto = convertToDto(findFirstWeatherSampleByCityIdAndTime(
                averageDto.getCityId(),
                averageDto.getTime()
        ));
        if (existingWeatherSampleDto != null) {
            existingWeatherSampleDto.setId(null);
        }
        if (existingWeatherSampleDto == null || !existingWeatherSampleDto.equals(averageDto)) {
            addWeatherSample(convertToEntity(averageDto));
        }
    }

    public WeatherSampleDto convertToDto(WeatherSample weatherSample) {
        if (weatherSample == null) {
            return null;
        }
        return mapper.convertValue(weatherSample, WeatherSampleDto.class);
    }

    public WeatherSample convertToEntity(WeatherSampleDto weatherSampleDto) {
        if (weatherSampleDto == null) {
            return null;
        }
        return mapper.convertValue(weatherSampleDto, WeatherSample.class);
    }

//        @PostConstruct
//    public void init() {
//        weatherSampleRepository.saveAll(Arrays.asList(
//                new WeatherSample("Ternopil", -0.99f, -7.32f, 1030, 82, 5, 691650, 1579826046, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.67f, -5.64f, 985, 61, 51, 691650, 1579899329, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 0f, -6.78f, 985, 64, 51, 691650, 1579902899, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 0.56f, -5.25f, 985, 63, 51, 691650, 1579903776, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580132973, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580133658, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580134637, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580134637, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.95f, -1.31f, 1016, 87, 99, 691650, 1580137114, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580143089, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580143552, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 1.06f, -2.27f, 1016, 84, 100, 691650, 1580144981, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 2.78f, -4.05f, 969, 76, 50, 691650, 1580322598, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 3.33f, 0.28f, 969, 74, 50, 691650, 1580324738, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 3.89f, -0.82f, 975, 79, 78, 691650, 1580372020, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 3.89f, 0.4f, 975, 78, 78, 691650, 1580373254, 49.56f, 25.61f),
//                new WeatherSample("Ternopil", 3.89f, -0.85f, 975, 78, 78, 691650, 158037420, 49.56f, 25.61f)
//                )
//        );
//    }
}
