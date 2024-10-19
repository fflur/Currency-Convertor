package org.core;

import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.scrapers.Currency;
import org.scrapers.IScraper;
import org.core.InvalidCurrencyCode;

public final class CurrencyConvertor {
    private HashMap<String, Currency> currencies;
    private IScraper scraper;

    public CurrencyConvertor(HashMap currencies, IScraper scraper) {
        this.currencies = currencies;
        this.scraper = scraper;
    }

    public double convert(String src, String dst, double mnt_to_cnvrt) throws
        InvalidCurrencyCode
    {
        if (!this.currencies.containsKey(src)) throw new InvalidCurrencyCode();
        if (!this.currencies.containsKey(dst)) throw new InvalidCurrencyCode();
        Currency curr_src = (Currency) this.currencies.get(src);
        Currency curr_dst = (Currency) this.currencies.get(dst);

        return (mnt_to_cnvrt / curr_src.getExchangeRate())
            * curr_dst.getExchangeRate();
    }

    public boolean isValidCurrency(String code) {
        return this.currencies.containsKey(code);
    }

    public void getCurrCodeByName() {}
    public void getInfo() {}
    public void listCurrencies() {}
    public void setBaseCurrency() {}
    public void getBaseCurrency() {}
    public void isBaseCurrencySet() {}
}
