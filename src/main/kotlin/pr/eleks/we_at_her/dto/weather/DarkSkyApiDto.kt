package pr.eleks.we_at_her.dto.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty
import pr.eleks.we_at_her.exceptions.ApiFieldNotFoundException

@JsonIgnoreProperties(ignoreUnknown = true)
data class DarkSkyApiDto(
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
    // No id, cityName, cityId

    @JsonProperty("currently")
    private fun unpackCurrently(currentlyObj: Map<String, String>) {
        temperature = (currentlyObj["temperature"]
                ?: throw ApiFieldNotFoundException("temperature"))
                .toFloat()
        feelsLike = (currentlyObj["apparentTemperature"]
                ?: throw ApiFieldNotFoundException("apparentTemperature"))
                .toFloat()
        pressure = (currentlyObj["pressure"]
                ?: throw ApiFieldNotFoundException("pressure"))
                .toFloat()
        humidity = ((currentlyObj["humidity"]
                ?: throw ApiFieldNotFoundException("humidity"))
                .toFloat() * 100)
                .toInt()
        clouds = ((currentlyObj["cloudCover"]
                ?: throw ApiFieldNotFoundException("cloudCover"))
                .toFloat() * 100)
                .toInt()
        time = (currentlyObj["time"]
                ?: throw ApiFieldNotFoundException("time"))
                .toInt()
    }
}