package org.scrapers;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Currency implements Serializable {
    private final String code;
    private final String name;
    private final double exchange_rate;

    public Currency(
        String code,
        String name,
        double exchange_rate
    ) {
        this.code = code;
        this.name = name;
        this.exchange_rate = exchange_rate;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public double getExchangeRate() {
        return this.exchange_rate;
    }
}
