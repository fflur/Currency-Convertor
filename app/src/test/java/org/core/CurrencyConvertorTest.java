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
    private Map currencies;

    public CurrencyConvertorTest() throws Exception {
        IScraper scraper = Mockito.mock(IScraper.class);
        Currency curren = Mockito.mock(Currency.class);
        this.currencies = new HashMap<String, Currency>();
        this.currencies.put("KWD", curren);
        this.cc = new CurrencyConvertor(this.currencies, scraper);
    }

    @Test
    void convertTest() {
        Assertions.assertEquals(
            200D,
            cc.convert("USD", "INR", 100D)
        );
    }

    @Test
    void getCurrCodeByCountryTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByCountry("aerica"));
        Assertions.assertEquals(null, cc.getCurrCodeByCountry("america"));
        Assertions.assertEquals("INR", cc.getCurrCodeByCountry("india"));
    }

    @Test
    void getCurrCodeByNameTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByName("asdkf"));
        Assertions.assertEquals(null, cc.getCurrCodeByName("dinaar"));
        Assertions.assertEquals("INR", cc.getCurrCodeByName("indian rupee"));
    }

    @Test
    void setBaseCurrencyTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.setBaseCurrency("KWD"));
    }

    @Test
    void getBaseCurrencyTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getBaseCurrency());
        Assertions.assertEquals("KWD", cc.getBaseCurrency());
    }

    @Test
    void isBaseCurrencySetTest() throws Exception {
        Assertions.assertTrue(cc.isBaseCurrencySet());
    }
}
