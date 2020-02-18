package pr.eleks.we_at_her.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherApiDto extends AbstractWeatherSampleDto {
    // No id
    @JsonProperty("name")
    String cityName;
    @JsonProperty("dt")
    int time;

    @JsonProperty("main")
    private void unpackMain(Map<String, String> mainObj) {
        temperature = Float.parseFloat(mainObj.get("temp"));
        feelsLike = Float.parseFloat(mainObj.get("feels_like"));
        pressure = Float.parseFloat(mainObj.get("pressure"));
        humidity = Integer.parseInt(mainObj.get("humidity"));
    }

    @JsonProperty("clouds")
    private void unpackClouds(Map<String, Integer> cloudsObj) {
        clouds = cloudsObj.get("all");
    }

    @JsonProperty("id")
    private void unpackId(Integer idObj) {
        cityId = idObj;
    }

    @JsonProperty("coord")
    private void unpackCoord(Map<String, Float> coordObj) {
        latitude = coordObj.get("lat");
        longitude = coordObj.get("lon");
    }
}
