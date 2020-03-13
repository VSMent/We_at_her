package pr.eleks.we_at_her.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class City(
        @Id
        var id: Long,
        var name: String,
        var stateName: String,
        var countryName: String,
        @Column(precision = 8, scale = 5)
        var latitude: Double,
        @Column(precision = 8, scale = 5)
        var longitude: Double
)