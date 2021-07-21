package com.example.europetour.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface CurrencyService {
    void seedCurrency() throws FileNotFoundException;
}
