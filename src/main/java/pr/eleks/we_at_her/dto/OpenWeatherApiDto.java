package pr.eleks.we_at_her.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherApiDto implements Serializable {
    @JsonProperty("name")
    private String cityName;    // Ternopil
    private float temperature;  // -1.4 - 15.8 (deg C)
    private float feelsLike;    // -1.4 - 15.8 (deg C)
    private float pressure;     // 10 - 90 (hPa - hecto Pascal)
    private int humidity;       // 10 - 90 (%)
    private int clouds;         // 10 - 90 (%)
    private int cityId;         // 691650
    @JsonProperty("dt")
    private int time;           // 1579825648 (unix, UTC)
    private float latitude;     // 49.55589
    private float longitude;    // 25.60556

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

    public OpenWeatherApiDto() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}