package com.example.europetour.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    private String Code;
    private String Name;
    private Currency currency;

    public Country() {
    }

    public Country(String abbreviation, String name) {
        this.Code = abbreviation;
        this.Name = name;
    }

    @Column(name = "country_code")
    public String getCode() {
        return Code;
    }

    public void setCode(String abbreviation) {
        this.Code = abbreviation;
    }

    @Column(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @ManyToOne
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
