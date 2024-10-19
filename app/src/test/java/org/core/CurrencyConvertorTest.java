package org.core;

import org.mockito.Mockito;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import org.scrapers.Currency;
import org.scrapers.IScraper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CurrencyConvertorTest {
    private CurrencyConvertor cc;
    private Map<String, Currency> currencies;

    public CurrencyConvertorTest() throws Exception {
        IScraper scraper = Mockito.mock(IScraper.class);

        Currency curren1 = new Currency(
            "USD",
            "US Dollar",
            10
        );

        Currency curren2 = new Currency(
            "INR",
            "Indian Rupee",
            1000
        );

        this.currencies = new HashMap<String, Currency>();
        this.currencies.put("USD", curren1);
        this.currencies.put("INR", curren2);
        this.cc = new CurrencyConvertor(this.currencies, scraper);
    }

    void convertTest() throws Exception {
        Assertions.assertEquals(100000D, cc.convert("USD", "INR", 1000D));
    }

    void getCurrCodeByCountryTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByCountry("aerica"));
        Assertions.assertEquals(null, cc.getCurrCodeByCountry("america"));
        Assertions.assertEquals("INR", cc.getCurrCodeByCountry("india"));
    }

    void getCurrCodeByNameTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByName("asdkf"));
        Assertions.assertEquals(null, cc.getCurrCodeByName("dinaar"));
        Assertions.assertEquals("INR", cc.getCurrCodeByName("indian rupee"));
    }

    void getInfoTest() throws Exception {
        Map<String, Currency> copied_map = this.currencies;
        Assertions.assertTrue(copied_map.values().equals(cc.getInfo()));
    }

    void listCurrenciesTest() {
        String tst[][] = {
            {"USD", "American Dollar"},
            {"INR", "Indian Rupee"}
        };

        Assertions.assertArrayEquals(tst, cc.listCurrencies());
    }

    void setBaseCurrencyTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.setBaseCurrency("INR"));
    }

    void getBaseCurrencyTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getBaseCurrency());
        Assertions.assertEquals("INR", cc.getBaseCurrency());
    }

    void isBaseCurrencySetTest() throws Exception {
        Assertions.assertTrue(cc.isBaseCurrencySet());
    }
}
