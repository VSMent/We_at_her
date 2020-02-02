package pr.eleks.we_at_her.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.dto.DarkSkyApiDto;
import pr.eleks.we_at_her.dto.OpenWeatherApiDto;
import pr.eleks.we_at_her.dto.WeatherBitApiDto;
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
    private String weatherbitJson = "{\"data\":[{\"rh\":73,\"pod\":\"d\",\"lon\":25.60556,\"pres\":966.083,\"timezone\":\"Europe\\/Kiev\",\"ob_time\":\"2020-02-02 11:12\",\"country_code\":\"UA\",\"clouds\":83,\"ts\":1580641921,\"solar_rad\":194.933,\"state_code\":\"22\",\"city_name\":\"Ternopil’\",\"wind_spd\":6.55996,\"last_ob_time\":\"2020-02-02T10:54:00\",\"wind_cdir_full\":\"захід-пі́вніч-захід\",\"wind_cdir\":\"ЗСЗ\",\"slp\":1005.87,\"vis\":24.1352,\"h_angle\":18,\"sunset\":\"15:12\",\"dni\":707.23,\"dewpt\":3.5,\"snow\":0,\"uv\":1.14156,\"precip\":0,\"wind_dir\":283,\"sunrise\":\"05:41\",\"ghi\":323.83,\"dhi\":80.62,\"aqi\":22,\"lat\":49.55589,\"weather\":{\"icon\":\"c04d\",\"code\":\"804\",\"description\":\"Похмурі хмари\"},\"datetime\":\"2020-02-02:11\",\"temp\":8,\"station\":\"F1141\",\"elev_angle\":23.25,\"app_temp\":8}],\"count\":1}\n";
    private String darkskyJson = "{\"latitude\":49.55589,\"longitude\":25.60556,\"timezone\":\"Europe/Kiev\",\"currently\":{\"time\":1580660167,\"summary\":\"Невелика Хмарність\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0.0048,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":6.14,\"apparentTemperature\":1.67,\"dewPoint\":1.91,\"humidity\":0.74,\"pressure\":1007.6,\"windSpeed\":7.94,\"windGust\":13.66,\"windBearing\":325,\"cloudCover\":0.39,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":335.9},\"offset\":2}";

    public WeatherSampleService(WeatherSampleRepository weatherSampleRepository, ObjectMapper mapper, Environment env) {
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

    public WeatherSampleDto getWeatherSampleFromOpenWeatherApi(String latitude, String longitude, String lang, String units) {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("OWApi.lang") : lang;
        units = units.equals("") ? env.getProperty("OWApi.units") : units;

        // Prepare request string
//        String apiUrl = "http://api.openweathermap.org/data/2.5";
        String apiUrl = env.getProperty("OWApi.baseUrl", "http://api.openweathermap.org/data/2.5");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("OWApi.request"))
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("appid", env.getProperty("OWApi.key"));

        // Make request
        RestTemplate restTemplate = new RestTemplate();
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

    public WeatherSampleDto getWeatherSampleFromWeatherBitApi(String latitude, String longitude, String lang, String units) {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("WBApi.lang") : lang;
        units = units.equals("") ? env.getProperty("WBApi.units") : units;

        // Prepare request string
        String apiUrl = env.getProperty("WBApi.baseUrl", "http://api.weatherbit.io/v2.0/");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("WBApi.request"))
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("key", env.getProperty("WBApi.key"));

        // Make request
        RestTemplate restTemplate = new RestTemplate();
        WeatherBitApiDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), WeatherBitApiDto.class);
//        WeatherBitApiDto apiResponseDto = null;
//        try {
//            apiResponseDto = mapper.readValue(weatherbitJson, WeatherBitApiDto.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        // Handle error, return result
        if (apiResponseDto != null) {
            return new WeatherSampleDto(
                    apiResponseDto.getCityName(),
                    apiResponseDto.getTemperature(),
                    apiResponseDto.getFeelsLike(),
                    apiResponseDto.getPressure(),
                    apiResponseDto.getHumidity(),
                    apiResponseDto.getClouds(),
                    -1,
                    apiResponseDto.getTime(),
                    apiResponseDto.getLatitude(),
                    apiResponseDto.getLongitude()
            );
        }
        return null;
    }

    public WeatherSampleDto getWeatherSampleFromDarkSkyApi(String latitude, String longitude, String lang, String units) {
        // Default values
        latitude = latitude.equals("") ? env.getProperty("city.Ternopil.lat") : latitude;
        longitude = longitude.equals("") ? env.getProperty("city.Ternopil.lon") : longitude;
        lang = lang.equals("") ? env.getProperty("DSApi.lang") : lang;
        units = units.equals("") ? env.getProperty("DSApi.units") : units;

        // Prepare request string
        String apiUrl = env.getProperty("DSApi.baseUrl", "https://api.darksky.net/");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .pathSegment(env.getProperty("DSApi.request"))
                .pathSegment(env.getProperty("DSApi.key"))
                .pathSegment(latitude + "," + longitude)
                .queryParam("lang", lang)
                .queryParam("units", units)
                .queryParam("exclude", env.getProperty("DSApi.exclude"));

        // Make request
        RestTemplate restTemplate = new RestTemplate();
        DarkSkyApiDto apiResponseDto = restTemplate.getForObject(uriBuilder.toUriString(), DarkSkyApiDto.class);
//        DarkSkyApiDto apiResponseDto = null;
//        try {
//            apiResponseDto = mapper.readValue(darkskyJson, DarkSkyApiDto.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        // Handle error, return result
        if (apiResponseDto != null) {
            return new WeatherSampleDto(
                    null,
                    apiResponseDto.getTemperature(),
                    apiResponseDto.getFeelsLike(),
                    apiResponseDto.getPressure(),
                    apiResponseDto.getHumidity(),
                    apiResponseDto.getClouds(),
                    -1,
                    apiResponseDto.getTime(),
                    apiResponseDto.getLatitude(),
                    apiResponseDto.getLongitude()
            );
        }
        return null;
    }

    public WeatherSampleDto addWeatherSampleFromApi() {
        WeatherSampleDto OpenWeatherDto = getWeatherSampleFromOpenWeatherApi("", "", "", "");
        WeatherSampleDto WeatherBitDto = getWeatherSampleFromWeatherBitApi("", "", "", "");
        WeatherSampleDto DarkSkyDto = getWeatherSampleFromDarkSkyApi("", "", "", "");
        if (OpenWeatherDto != null) {
            WeatherSampleDto existingWeatherSampleDto = convertToDto(findFirstWeatherSampleByCityIdAndTime(
                    OpenWeatherDto.getCityId(),
                    OpenWeatherDto.getTime()
            ));
            if (existingWeatherSampleDto != null) {
                existingWeatherSampleDto.setId(null);
            }
            if (existingWeatherSampleDto == null || !existingWeatherSampleDto.equals(OpenWeatherDto)) {
                addWeatherSample(convertToEntity(OpenWeatherDto));
            }
            return OpenWeatherDto;
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
