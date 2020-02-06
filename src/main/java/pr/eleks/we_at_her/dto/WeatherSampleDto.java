package pr.eleks.we_at_her.dto;


public class WeatherSampleDto extends AbstractWeatherSampleDto {
    public WeatherSampleDto() {
    }

    public WeatherSampleDto(String cityName, float temperature, float feelsLike, float pressure, int humidity, int clouds, int cityId, int time, float latitude, float longitude) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds = clouds;
        this.cityId = cityId;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
