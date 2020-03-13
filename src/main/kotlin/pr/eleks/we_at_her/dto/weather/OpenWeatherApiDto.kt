package pr.eleks.we_at_her.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import pr.eleks.we_at_her.exceptions.ApiFieldNotFoundException

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpenWeatherApiDto(
        @JsonProperty("_id")
        override var id: Long? = null,
        override var cityName: String? = null,
        override var temperature: Float? = null,
        override var feelsLike: Float? = null,
        override var pressure: Float? = null,
        override var humidity: Int? = null,
        @JsonProperty("_clouds")
        override var clouds: Int? = null,
        override var cityId: Int? = null,
        override var time: Int? = null,
        override var latitude: Double = 0.0,
        override var longitude: Double = 0.0
) : AbstractWeatherSampleDto() {
    @JsonProperty("name")
    private fun unpackName(name: String?) {
        cityName = name ?: throw ApiFieldNotFoundException("name")
    }

    @JsonProperty("main")
    private fun unpackMain(mainObj: Map<String, String>) {
        temperature = (mainObj["temp"]
                ?: throw ApiFieldNotFoundException("temp"))
                .toFloat()
        feelsLike = (mainObj["feels_like"]
                ?: throw ApiFieldNotFoundException("feels_like"))
                .toFloat()
        pressure = (mainObj["pressure"]
                ?: throw ApiFieldNotFoundException("pressure"))
                .toFloat()
        humidity = (mainObj["humidity"]
                ?: throw ApiFieldNotFoundException("humidity"))
                .toInt()
    }

    @JsonProperty("clouds")
    private fun unpackClouds(cloudsObj: Map<String, Int>) {
        clouds = cloudsObj["all"]
                ?: throw ApiFieldNotFoundException("clouds")
    }

    @JsonProperty("dt")
    private fun unpackDt(dt: Int?) {
        time = dt ?: throw ApiFieldNotFoundException("dt")
    }

    @JsonProperty("id")
    private fun unpackId(idObj: Int?) {
        cityId = idObj
                ?: throw ApiFieldNotFoundException("id")
    }

    @JsonProperty("coord")
    private fun unpackCoord(coordObj: Map<String, Double>) {
        latitude = coordObj["lat"]
                ?: throw ApiFieldNotFoundException("lat")
        longitude = coordObj["lon"]
                ?: throw ApiFieldNotFoundException("lon")
    }
}
