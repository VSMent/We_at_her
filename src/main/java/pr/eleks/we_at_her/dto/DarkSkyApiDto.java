package pr.eleks.we_at_her.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyApiDto implements Serializable {
    private float temperature;  // -1.4 - 15.8 (deg C)
    private float feelsLike;    // -1.4 - 15.8 (deg C)
    private float pressure;     // 10 - 90 (hPa - hecto Pascal)
    private int humidity;       // 0.1 -> 10 (%)
    private int clouds;         // 0.1 -> 10 (%)
    private int time;           // 1579825648 (unix, UTC)
    private float latitude;     // 49.55589
    private float longitude;    // 25.60556

    @JsonProperty("currently")
    private void unpackCurrently(Map<String, String> currentlyObj) {
        temperature = Float.parseFloat(currentlyObj.get("temperature"));
        feelsLike = Float.parseFloat(currentlyObj.get("apparentTemperature"));
        pressure = Float.parseFloat(currentlyObj.get("pressure"));
        humidity = (int) (Float.parseFloat(currentlyObj.get("humidity")) * 100);
        clouds = (int) (Float.parseFloat(currentlyObj.get("cloudCover")) * 100);
        time = Integer.parseInt(currentlyObj.get("time"));
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