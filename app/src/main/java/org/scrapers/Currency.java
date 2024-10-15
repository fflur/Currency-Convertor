package org.scrapers;

public class Currency {
    private String code;
    private String name;
    private String country;
    private double exchange_rate;

    public Currency(
        String code,
        String name,
        String country,
        double exchange_rate
    ) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.exchange_rate = exchange_rate;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setName(String name) {
        this.name = name;
    }

    void setCountry(String country) {
        this.country = country;
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

    public String getCountry() {
        return this.country;
    }

    public double getExchangeRate() {
        return this.exchange_rate;
    }
}
