package org.core;

import org.core.CurrencyConvertor;
import org.scrapers.IScraper;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurrencyConvertorTest {
    private IScraper mocked_iscraper = Mockito.mock(IScraper.class);
    private CurrencyConvertor currcon = new CurrencyConvertor(mocked_iscraper);

    @Test
    public void convertTest() {
        Assertions.assertEquals(
          100,
          currcon.convert("USD", "INR", 100D),
          "result should always be 200"
        );
    }
}
