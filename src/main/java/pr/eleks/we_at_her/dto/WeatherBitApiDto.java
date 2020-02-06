package pr.eleks.we_at_her.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitApiDto implements Serializable {
    private String cityName;    // Ternopil
    private float temperature;  // -1.4 - 15.8 (deg C)
    private float feelsLike;    // -1.4 - 15.8 (deg C)
    private float pressure;     // 10 - 90 (1.0 milibar == 1.0 hecto Pascal)
    private int humidity;       // 10 -90 (%)
    private int clouds;         // 10 -90 (%)
    private int time;           // 1579825648 (unix)
    private float latitude;     // 49.55589
    private float longitude;    // 25.60556

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
