package org.scrapers;

import java.util.List;
import java.util.ArrayList;
import nu.xom.Element; 

public class Currency {
    private String code;
    private String name;
    private int number;
    private List<String> countries = new ArrayList<String>();
    private double exchange_rate = 0.0;

    public Currency(
        String code,
        String name,
        int number
    ) {
        this.code = code;
        this.name = name;
        this.number = number;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setName(String name) {
        this.name = name;
    }

    public void addCountry(String country) {
        this.countries.add(country);
    }

    void addCountries(List<String> countries) {
        this.countries.addAll(countries);
    }

    void setExchangeRate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getCountries() {
        return this.countries;
    }

    public double getExchangeRate() {
        return this.exchange_rate;
    }
}
