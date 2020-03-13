package pr.eleks.we_at_her.repositories

import org.springframework.data.repository.CrudRepository
import pr.eleks.we_at_her.entities.WeatherSample
import java.util.*

interface WeatherSampleRepository : CrudRepository<WeatherSample, Long> {
    fun findFirstByCityIdAndTime(cityId: Int, time: Int): WeatherSample?
}