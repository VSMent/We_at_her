package pr.eleks.we_at_her.dto.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyApiDto extends AbstractWeatherSampleDto {
    // No id, cityName, cityId, lat, lon
    @JsonProperty("currently")
    private void unpackCurrently(Map<String, String> currentlyObj) {
        temperature = Float.parseFloat(currentlyObj.get("temperature"));
        feelsLike = Float.parseFloat(currentlyObj.get("apparentTemperature"));
        pressure = Float.parseFloat(currentlyObj.get("pressure"));
        humidity = (int) (Float.parseFloat(currentlyObj.get("humidity")) * 100);
        clouds = (int) (Float.parseFloat(currentlyObj.get("cloudCover")) * 100);
        time = Integer.parseInt(currentlyObj.get("time"));
    }
}