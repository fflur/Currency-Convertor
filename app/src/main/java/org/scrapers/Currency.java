package org.scrapers;

public class Currency {
    private String code;
    private String name;
    private String country;
    private int exchange_rate;

    public Currency(
        String code,
        String name,
        String country,
        int exchange_rate
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

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }
}
