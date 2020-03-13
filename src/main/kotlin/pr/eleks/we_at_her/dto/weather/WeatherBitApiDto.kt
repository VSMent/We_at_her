package pr.eleks.we_at_her.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import pr.eleks.we_at_her.exceptions.ApiFieldNotFoundException
import kotlin.reflect.jvm.jvmName

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherBitApiDto(
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
    // No id, cityId

    @JsonProperty("data")
    private fun unpackMain(data: Array<Map<String, Any>>) {
        cityName = (data[0]["city_name"]
                ?: throw ApiFieldNotFoundException("city_name"))
                .toString()
        temperature = (data[0]["temp"]
                ?: throw ApiFieldNotFoundException("temp"))
                .toString()
                .toFloat()
        feelsLike = (data[0]["app_temp"]
                ?: throw ApiFieldNotFoundException("app_temp"))
                .toString()
                .toFloat()
        pressure = (data[0]["pres"]
                ?: throw ApiFieldNotFoundException("pres"))
                .toString()
                .toFloat()
        humidity = (data[0]["rh"]
                ?: throw ApiFieldNotFoundException("rh"))
                .toString()
                .toInt()
        clouds = (data[0]["clouds"]
                ?: throw ApiFieldNotFoundException("clouds"))
                .toString()
                .toInt()
        time = (data[0]["ts"]
                ?: throw ApiFieldNotFoundException("ts"))
                .toString()
                .toInt()
        latitude = (data[0]["lat"]
                ?: throw ApiFieldNotFoundException("lat"))
                .toString()
                .toDouble()
        longitude = (data[0]["lon"]
                ?: throw ApiFieldNotFoundException("lon"))
                .toString()
                .toDouble()
    }
}
