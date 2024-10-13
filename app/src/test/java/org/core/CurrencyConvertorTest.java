package org.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyConvertorTest {
    private CurrencyConvertor cc = new CurrencyConvertor();

    @Test
    void convertTest() {
        Assertions.assertEquals(
            100D,
            cc.convert("USD", "INR", 100D),
            "result should always be 200"
        );
    }
}
