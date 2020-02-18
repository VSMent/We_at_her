package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.entities.City;
import pr.eleks.we_at_her.repositories.CityRepository;
import pr.eleks.we_at_her.services.data.CityService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {
    private ObjectMapper mapper;
    private CityRepository cityRepository;

    public CityServiceImpl(ObjectMapper mapper, CityRepository cityRepository) {
        this.mapper = mapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> findAllCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CityDto convertToDto(City city) {
        if (city == null) {
            return null;
        }
        return mapper.convertValue(city, CityDto.class);
    }

    @Override
    public City convertToEntity(CityDto cityDto) {
        if (cityDto == null) {
            return null;
        }
        return mapper.convertValue(cityDto, City.class);
    }

//    @PostConstruct
//    void init() throws IOException {
//        try (Stream<String> s = Files.lines(Paths.get("src/main/resources/public/data/main_cities_ua.json"))) {
//            String citiesJson = s.collect(Collectors.joining());
//            ArrayList<CityDto> cityDtos = new ArrayList<>(Arrays.asList(mapper.readValue(citiesJson, CityDto[].class)));
//            cityDtos.stream().map(this::convertToEntity).forEach(cityRepository::save);
//        }
//    }
}
