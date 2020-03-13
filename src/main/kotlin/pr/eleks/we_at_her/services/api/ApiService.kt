package pr.eleks.we_at_her.services.api;

import pr.eleks.we_at_her.dto.weather.WeatherSampleDto;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.exceptions.WrongApiResponseException;

interface ApiService {
    fun getWeatherSampleFromApi(latitude: String?, longitude: String?, lang: String?, units: String?): WeatherSampleDto
    fun getName(): String
    fun prepareParameters(latitude: String?, longitude: String?, lang: String?, units: String?)
    fun prepareBaseUrl()
}
