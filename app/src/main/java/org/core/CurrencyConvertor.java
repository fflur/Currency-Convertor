package org.core;

import java.util.Map;
import org.scrapers.Currency;
import org.scrapers.IScraper;

public class CurrencyConvertor {
    private Map currencies;
    private IScraper scraper;

    public CurrencyConvertor(Map currencies, IScraper scraper){
        this.currencies = currencies;
        this.scraper = scraper;
    }

    public double convert(String src, String dest, double mnt_to_cnvrt) {
        return 2 * mnt_to_cnvrt;
    }

    public void convertAll() {}

    public boolean isValidCurrency(Currency curr) {
        if (this.currencies.containsKey(curr.getCode())) return true;
        return false;
    }

    public void search() {}
    public void getInfo() {}
    public void listCurrencies() {}
    public void setBaseCurrency() {}
    public void getBaseCurrency() {}
    public void isBaseCurrencySet() {}
}
