package org.scrapers;

import java.util.List;

public class Currency {
    private String code;
    private String name;
    private List<String> countries = null;
    private double exchange_rate;

    public Currency(
        String code,
        String name,
        double exchange_rate
    ) {
        this.code = code;
        this.name = name;
        this.exchange_rate = exchange_rate;
    }

    public Currency(
        String code,
        String name,
        List<String> countries,
        double exchange_rate
    ) {
        this.code = code;
        this.name = name;
        this.countries = countries;
        this.exchange_rate = exchange_rate;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setName(String name) {
        this.name = name;
    }

    void addCountry(String country) {
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
