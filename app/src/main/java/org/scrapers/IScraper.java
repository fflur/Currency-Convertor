package org.scrapers;

import java.util.HashMap;
import org.scrapers.Currency;

public interface IScraper {
    void scrape();
    void produceCurrencies();
    HashMap<String, Currency> getData();
}
