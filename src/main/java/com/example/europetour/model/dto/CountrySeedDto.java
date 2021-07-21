package com.example.europetour.model.dto;

import com.example.europetour.model.entity.Country;
import com.google.gson.annotations.Expose;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CountrySeedDto {
    @Expose
    private String Name;
    @Expose
    private String Code;
    @Expose
    private String currencyCode;

    public CountrySeedDto() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
