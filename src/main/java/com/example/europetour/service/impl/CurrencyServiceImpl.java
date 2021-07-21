package com.example.europetour.service.impl;

import com.example.europetour.model.dto.CurrencySeedDto;
import com.example.europetour.model.entity.Currency;
import com.example.europetour.repository.CurrencyRepository;
import com.example.europetour.service.CurrencyService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import static com.example.europetour.constants.GlobalConstants.CURRENCY_SEED_JSON;
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final Gson gson;
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(Gson gson, CurrencyRepository currencyRepository) {
        this.gson = gson;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void seedCurrency() throws FileNotFoundException {

        if (currencyRepository.count() > 0) {
            return;
        }

        CurrencySeedDto currencySeedDtos = gson.fromJson(new FileReader(CURRENCY_SEED_JSON), CurrencySeedDto.class);

        ArrayList<Map.Entry<String, BigDecimal>> entries = new ArrayList<>(currencySeedDtos.getRates().entrySet());
        for (Map.Entry<String, BigDecimal> entry : entries) {
            Currency currency = new Currency();
            currency.setCurrencyCode(entry.getKey());
            currency.setRateToEuro(entry.getValue());
            currencyRepository.save(currency);
        }
    }
}
