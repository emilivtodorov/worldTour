package com.example.europetour.service.impl;

import com.example.europetour.model.dto.CountrySeedDto;
import com.example.europetour.model.entity.Country;
import com.example.europetour.repository.CountryRepository;
import com.example.europetour.repository.CurrencyRepository;
import com.example.europetour.service.CountryService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import static com.example.europetour.constants.GlobalConstants.*;

@Service
public class CountryServiceImpl implements CountryService {


    private final CountryRepository countryRepository;
    private final CurrencyRepository currencyRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CurrencyRepository currencyRepository, Gson gson, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.currencyRepository = currencyRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCountries() throws IOException {

        if (countryRepository.count() > 0) {
            return;
        }

        CountrySeedDto[] countrySeedDtos = gson.fromJson(new FileReader(COUNTRY_SEED_JSON), CountrySeedDto[].class);

        for (CountrySeedDto countrySeedDto : countrySeedDtos) {
            Country country = new Country();
            country.setName(countrySeedDto.getName());
            country.setCode(countrySeedDto.getCode());
            country.setCurrency(currencyRepository.findCurrencyByCurrencyCode(countrySeedDto.getCurrencyCode()));
            countryRepository.save(country);
        }

    }
}

