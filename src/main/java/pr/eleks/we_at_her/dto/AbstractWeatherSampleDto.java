package pr.eleks.we_at_her.dto;

import java.io.Serializable;

public abstract class AbstractWeatherSampleDto implements Serializable {
    protected Long id;
    protected String cityName;    // Ternopil
    protected float temperature;  // -1.4 - 15.8 (deg C)
    protected float feelsLike;    // -1.4 - 15.8 (deg C)
    protected float pressure;     // 10 - 90 (hPa - hecto Pascal)
    protected int humidity;       // 10 - 90 (%)
    protected int clouds;         // 10 - 90 (%)
    protected int cityId;         // 691650
    protected int time;           // 1579825648 (unix)
    protected float latitude;     // 49.55589
    protected float longitude;    // 25.60556

    public AbstractWeatherSampleDto() {
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
