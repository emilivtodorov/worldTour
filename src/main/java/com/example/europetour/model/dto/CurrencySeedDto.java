package com.example.europetour.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CurrencySeedDto {

    private Map<String, BigDecimal> rates;

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
