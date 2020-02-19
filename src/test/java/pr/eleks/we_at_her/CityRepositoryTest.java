package pr.eleks.we_at_her;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pr.eleks.we_at_her.entities.City;
import pr.eleks.we_at_her.repositories.CityRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SpringJUnitConfig(Application.class)
public class CityRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(CityRepositoryTest.class);
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void city_SaveFind() {
        String cityName = "City";
        String stateName = "State";
        String countryName = "Country";
        double latitude = 50.26487;
        double longitude = 28.67669;
        long cityId = 686967L;
        City city = new City();

        city.setName(cityName);
        city.setStateName(stateName);
        city.setCountryName(countryName);
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        city.setId(cityId);

        cityRepository.save(city);

        City foundCity = cityRepository.findById(cityId).orElse(null);

        assertThat(foundCity).isNotNull();
        assertThat(city.getName())
                .isNotBlank()
                .isEqualTo(foundCity.getName());
        assertThat(city.getStateName())
                .isNotBlank()
                .isEqualTo(foundCity.getStateName());
        assertThat(city.getCountryName())
                .isNotBlank()
                .isEqualTo(foundCity.getCountryName());
        assertThat(city.getId())
                .isNotNull()
                .isEqualTo(foundCity.getId());
        assertThat(city.getLatitude())
                .isEqualTo(foundCity.getLatitude());
        assertThat(city.getLongitude())
                .isEqualTo(foundCity.getLongitude());
    }
}

