package pr.eleks.we_at_her.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitApiDto extends AbstractWeatherSampleDto {
    // No id, cityId
    @JsonProperty("data")
    private void unpackMain(Map<String, Object>[] data) {
        cityName = data[0].get("city_name").toString();
        temperature = Float.parseFloat(data[0].get("temp").toString());
        feelsLike = Float.parseFloat(data[0].get("app_temp").toString());
        pressure = Float.parseFloat(data[0].get("pres").toString());
        humidity = Integer.parseInt(data[0].get("rh").toString());
        clouds = Integer.parseInt(data[0].get("clouds").toString());
        time = Integer.parseInt(data[0].get("ts").toString());
        latitude = Float.parseFloat(data[0].get("lat").toString());
        longitude = Float.parseFloat(data[0].get("lon").toString());
    }
}
