package pr.eleks.we_at_her.dto.weather;


data class WeatherSampleDto(
        override var id: Long? = null,
        override var cityName: String? = null,
        override var temperature: Float? = null,
        override var feelsLike: Float? = null,
        override var pressure: Float? = null,
        override var humidity: Int? = null,
        override var clouds: Int? = null,
        override var cityId: Int? = null,
        override var time: Int? = null,
        override var latitude: Double = 0.0,
        override var longitude: Double = 0.0
) : AbstractWeatherSampleDto() {
    constructor(cityName: String?, temperature: Float?, feelsLike: Float?, pressure: Float?, humidity: Int?,
                clouds: Int?, cityId: Int?, time: Int?, latitude: Double, longitude: Double
    ) : this(null, cityName, temperature, feelsLike, pressure, humidity,
            clouds, cityId, time, latitude, longitude
    )
}