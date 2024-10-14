package org.core;

import org.mockito.Mockito;
import java.util.Map;
import java.io.File;
import org.scrapers.IScraper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CurrencyConvertorTest {
    private Map map = Mockito.mock(Map.class);
    private IScraper scraper = Mockito.mock(IScraper.class);
    private CurrencyConvertor cc = new CurrencyConvertor(map, scraper);

    @Test
    void convertTest() {
        Assertions.assertEquals(
            200D,
            cc.convert("USD", "INR", 100D)
        );
    }

    @Test
    void getCountryCodeTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> cc.getCountryCode("america"));
        Assertions.assertEquals(null, cc.getCountryCode("america"));
        Assertions.assertEquals("INR", cc.getCountryCode("india"));
    }
}
