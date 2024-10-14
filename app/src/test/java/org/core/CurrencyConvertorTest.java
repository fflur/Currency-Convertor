package org.core;

import org.mockito.Mockito;
import java.util.Map;
import java.io.File;
import org.scrapers.IScraper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CurrencyConvertorTest {
    private CurrencyConvertor cc;

    public CurrencyConvertorTest() throws Exception {
        Map map = Mockito.mock(Map.class);
        IScraper scraper = Mockito.mock(IScraper.class);
        this.cc = new CurrencyConvertor(map, scraper);
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
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByCountry("america"));
        Assertions.assertEquals(null, cc.getCurrCodeByCountry("america"));
        Assertions.assertEquals("INR", cc.getCurrCodeByCountry("india"));
    }

    @Test
    void getCurrCodeByNameTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCurrCodeByName("asdkf"));
        Assertions.assertEquals(null, cc.getCurrCodeByName("dinaar"));
        Assertions.assertEquals("INR", cc.getCurrCodeByName("indian rupee"));
  }
}
