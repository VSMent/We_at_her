package apiDto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pr.eleks.we_at_her.dto.weather.OpenWeatherApiDto
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

internal class OpenWeatherApiDtoTest {


    @Test
    fun createDto() {
        val owApiDto = OpenWeatherApiDto()
        val time = 123
        val name = "Random"
        val clouds = 123
        val lon = 123.123
        val cityId = 1232

        owApiDto.time = time
        owApiDto.cityName = name
        owApiDto.clouds = clouds
        owApiDto.longitude = lon
        owApiDto.cityId = cityId

        println(owApiDto)

        assertThat(owApiDto)
                .isNotNull
                .hasFieldOrPropertyWithValue("time", time)
                .hasFieldOrPropertyWithValue("cityName", name)
                .hasFieldOrPropertyWithValue("clouds", clouds)
                .hasFieldOrPropertyWithValue("longitude", lon)
                .hasFieldOrPropertyWithValue("cityId", cityId)
    }

    @Test
    fun createFromFile() {
        val mapper = jacksonObjectMapper()
        Files.lines(Paths.get("src/test/resources/data/OWApi.json")).use { s ->
            val json: String = s.collect(Collectors.joining())
            val ws: OpenWeatherApiDto = mapper.readValue(json)
            println(ws)
            assertThat(ws)
                    .isNotNull
                    .extracting("id").isEqualTo(null)
                    .extracting("cityId").isEqualTo(691650)
                    .extracting("clouds").isEqualTo(69)
        }
    }

    @Test
    fun idCheck() {
        val dto: OpenWeatherApiDto = jacksonObjectMapper().readValue("""{"id":12345}""")
        println(dto)
        assertThat(dto)
                .isNotNull
                .hasFieldOrPropertyWithValue("id", null)
                .hasFieldOrPropertyWithValue("cityId",12345)
    }

}