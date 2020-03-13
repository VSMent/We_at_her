package pr.eleks.we_at_her.dto.weather

abstract class AbstractWeatherSampleDto {
    abstract var id: Long?
    abstract var cityName: String?      // Ternopil
    abstract var temperature: Float?    // -1.4 - 15.8 (deg C)
    abstract var feelsLike: Float?      // -1.4 - 15.8 (deg C)
    abstract var pressure: Float?       // 10 - 90 (hPa - hecto Pascal)
    abstract var humidity: Int?         // 10 - 90 (%)
    abstract var clouds: Int?           // 10 - 90 (%)
    abstract var cityId: Int?           // 691650
    abstract var time: Int?             // 1579825648 (unix)
    abstract var latitude: Double       // 49.55589
    abstract var longitude: Double      // 25.60556
}