package com.example.europetour.service;

import com.example.europetour.model.entity.Country;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CountryService<List> {
    void seedCountries() throws IOException;

}
