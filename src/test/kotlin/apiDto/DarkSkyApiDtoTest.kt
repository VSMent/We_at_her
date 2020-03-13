package apiDto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pr.eleks.we_at_her.dto.weather.DarkSkyApiDto
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class DarkSkyApiDtoTest {

    @Test
    fun createFromFile() {
        val mapper = jacksonObjectMapper()
        Files.lines(Paths.get("src/test/resources/data/DSApi.json")).use { s ->
            val json: String = s.collect(Collectors.joining())
            val ws: DarkSkyApiDto = mapper.readValue(json)
            println(ws)
            assertThat(ws)
                    .isNotNull
        }
    }
}