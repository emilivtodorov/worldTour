package com.example.europetour.service.impl;

import com.example.europetour.model.dto.NeighboursSeedDto;
import com.example.europetour.model.entity.Country;
import com.example.europetour.model.entity.Neighbours;
import com.example.europetour.repository.CountryRepository;
import com.example.europetour.repository.NeighboursRepository;
import com.example.europetour.service.NeighboursService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import static com.example.europetour.constants.GlobalConstants.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

@Service
public class NeighboursServiceImpl implements NeighboursService {

    private final Gson gson;
    private final CountryRepository countryRepository;
    private final NeighboursRepository neighboursRepository;

    public NeighboursServiceImpl(Gson gson, CountryRepository countryRepository, NeighboursRepository neighboursRepository) {
        this.gson = gson;
        this.countryRepository = countryRepository;
        this.neighboursRepository = neighboursRepository;
    }

    @Override
    public void seedNeighbours() throws FileNotFoundException {

        if(neighboursRepository.count() > 0 ) {
            return;
        }

        NeighboursSeedDto[] neighboursSeedDtos = gson.fromJson(new FileReader(COUNTRY_SEED_JSON), NeighboursSeedDto[].class);
        for (NeighboursSeedDto neighboursSeedDto : neighboursSeedDtos) {
            Country currentCountry = countryRepository.findCountryByCode(neighboursSeedDto.getCode());
            Set<String> neighbours = neighboursSeedDto.getNeighbours();
            List<Country> allNeighboursCountries = countryRepository.findAllByCodeIn(neighbours);
            for (Country country : allNeighboursCountries) {
                Neighbours neighbours1 = new Neighbours();
                neighbours1.setNeighbour(currentCountry);
                neighbours1.setCountry(country);
                neighboursRepository.save(neighbours1);
            }
        }
    }
}
