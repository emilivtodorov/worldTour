package com.example.europetour.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity{

    private String currencyCode;
    private BigDecimal rateToEuro;


    public Currency() {
    }
    @Column(name = "currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    @Column(name = "rate_to_euro")
    public BigDecimal getRateToEuro() {
        return rateToEuro;
    }

    public void setRateToEuro(BigDecimal rateToEuro) {
        this.rateToEuro = rateToEuro;
    }
}
