package pr.eleks.we_at_her;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.entities.WeatherSample;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherSampleDtoUnitTest {
    private ObjectMapper modelMapper = new ObjectMapper();

    @Test
    public void whenConvertWeatherSampleEntityToWeatherSampleDto_thenCorrect() {
        WeatherSample weatherSample = new WeatherSample();
        weatherSample.setId(1L);
        weatherSample.setCityId(691650);
        weatherSample.setCityName("Ternopil");
        weatherSample.setClouds(71);
        weatherSample.setFeelsLike(-5.46f);
        weatherSample.setHumidity(68);
        weatherSample.setPressure(1022);
        weatherSample.setTemperature(-0.49f);
        weatherSample.setTime(1579884814);

        WeatherSampleDto weatherSampleDto = modelMapper.convertValue(weatherSample, WeatherSampleDto.class);
        assertEquals(weatherSample.getId(), weatherSampleDto.getId());
        assertEquals(weatherSample.getCityId(), weatherSampleDto.getCityId());
        assertEquals(weatherSample.getCityName(), weatherSampleDto.getCityName());
        assertEquals(weatherSample.getClouds(), weatherSampleDto.getClouds());
        assertEquals(weatherSample.getFeelsLike(), weatherSampleDto.getFeelsLike());
        assertEquals(weatherSample.getHumidity(), weatherSampleDto.getHumidity());
        assertEquals(weatherSample.getPressure(), weatherSampleDto.getPressure());
        assertEquals(weatherSample.getTemperature(), weatherSampleDto.getTemperature());
        assertEquals(weatherSample.getTime(), weatherSampleDto.getTime());
    }

    @Test
    public void whenConvertWeatherSampleDtoToWeatherSampleEntity_thenCorrect() {
        WeatherSampleDto weatherSampleDto = new WeatherSampleDto();
        weatherSampleDto.setId(1L);
        weatherSampleDto.setCityId(691650);
        weatherSampleDto.setCityName("Ternopil");
        weatherSampleDto.setClouds(71);
        weatherSampleDto.setFeelsLike(-5.46f);
        weatherSampleDto.setHumidity(68);
        weatherSampleDto.setPressure(1022);
        weatherSampleDto.setTemperature(-0.49f);
        weatherSampleDto.setTime(1579884814);

        WeatherSample weatherSample = modelMapper.convertValue(weatherSampleDto, WeatherSample.class);
        assertEquals(weatherSampleDto.getId(), weatherSample.getId());
        assertEquals(weatherSampleDto.getCityId(), weatherSample.getCityId());
        assertEquals(weatherSampleDto.getCityName(), weatherSample.getCityName());
        assertEquals(weatherSampleDto.getClouds(), weatherSample.getClouds());
        assertEquals(weatherSampleDto.getFeelsLike(), weatherSample.getFeelsLike());
        assertEquals(weatherSampleDto.getHumidity(), weatherSample.getHumidity());
        assertEquals(weatherSampleDto.getPressure(), weatherSample.getPressure());
        assertEquals(weatherSampleDto.getTemperature(), weatherSample.getTemperature());
        assertEquals(weatherSampleDto.getTime(), weatherSample.getTime());
    }
}