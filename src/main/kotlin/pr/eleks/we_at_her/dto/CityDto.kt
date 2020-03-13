package pr.eleks.we_at_her.dto

data class CityDto(
        var id: Long?,
        var name: String?,
        var stateName: String?,
        var countryName: String?,
        var latitude: Double,
        var longitude: Double
)