package pr.eleks.we_at_her.services.data;

import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.entities.City;

import java.util.List;

public interface CityService {
    List<CityDto> findAllCities();

    CityDto convertToDto(City city);

    City convertToEntity(CityDto cityDto);
}
