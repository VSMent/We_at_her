package pr.eleks.we_at_her.repositories;

import org.springframework.data.repository.CrudRepository;
import pr.eleks.we_at_her.entities.WeatherSample;

import java.util.Optional;

public interface WeatherSampleRepository extends CrudRepository<WeatherSample, Long> {
    Optional<WeatherSample> findFirstByCityIdAndTime(int cityId, int time);
}
// TODO fix this returning null