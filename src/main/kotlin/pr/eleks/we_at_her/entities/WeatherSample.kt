package pr.eleks.we_at_her.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class WeatherSample(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var cityName: String?,      // Ternopil
        var temperature: Float?,    // -1.4 - 15.8 (deg C)
        var feelsLike: Float?,      // -1.4 - 15.8 (deg C)
        var pressure: Float?,       // 10 - 90 (hPa - hecto Pascal)
        var humidity: Int?,         // 10 - 90 (%)
        var clouds: Int?,           // 10 - 90 (%)
        var cityId: Int?,           // 691650
        var time: Int?,             // 1579825648 (unix)
        var latitude: Double,       // 49.55589
        var longitude: Double       // 25.60556
)