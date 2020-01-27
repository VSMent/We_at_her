package pr.eleks.we_at_her.dto;


import java.io.Serializable;
import java.util.Objects;

public class WeatherSampleDto implements Serializable {
    private Long id;
    private String cityName;    // Ternopil
    private float temperature;  // -1.4 - 15.8 deg C
    private float feelsLike;    // -1.4 - 15.8 deg C
    private int pressure;       // 10 - 90 %
    private int humidity;       // 10 -90 %
    private int clouds;         // 10 -90 %
    private int cityId;         // 691650
    private int time;           // 1579825648

    public WeatherSampleDto() {
    }

    public WeatherSampleDto(String cityName, float temperature, float feelsLike, int pressure, int humidity, int clouds, int cityId, int time) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds = clouds;
        this.cityId = cityId;
        this.time = time;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherSampleDto that = (WeatherSampleDto) o;
        return Float.compare(that.getTemperature(), getTemperature()) == 0 &&
                Float.compare(that.getFeelsLike(), getFeelsLike()) == 0 &&
                getPressure() == that.getPressure() &&
                getHumidity() == that.getHumidity() &&
                getClouds() == that.getClouds() &&
                getCityId() == that.getCityId() &&
                getTime() == that.getTime() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCityName(), that.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCityName(), getTemperature(), getFeelsLike(), getPressure(), getHumidity(), getClouds(), getCityId(), getTime());
    }
}
