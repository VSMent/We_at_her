package pr.eleks.we_at_her.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiWeatherSampleDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonProperty("name")
    private String cityName;    // Ternopil
    private float temperature;  // -1.4 - 15.8 deg C
    private float feelsLike;    // -1.4 - 15.8 deg C
    private int pressure;       // 10 - 90 %
    private int humidity;       // 10 -90 %
    private int clouds;         // 10 -90 %
    private int cityId;         // 691650
    @JsonProperty("dt")
    private int time;           // 1579825648

    @JsonProperty("main")
    private void unpackMain(Map<String, String> main) {
        temperature = Float.parseFloat(main.get("temp"));
        feelsLike = Float.parseFloat(main.get("feels_like"));
        pressure = Integer.parseInt(main.get("pressure"));
        humidity = Integer.parseInt(main.get("humidity"));

    }

    @JsonProperty("clouds")
    private void unpackClouds(Map<String, Integer> cloudsObj) {
        clouds = cloudsObj.get("all");
    }

    @JsonProperty("id")
    private void unpackId(Integer idObj) {
        cityId = idObj;
    }

    public ApiWeatherSampleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
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
}
