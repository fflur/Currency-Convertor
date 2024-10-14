package org.core;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.XMLException;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.charset.StandardCharsets;
import org.scrapers.Currency;
import org.scrapers.IScraper;

public class CurrencyConvertor {
    private Map currencies;
    private IScraper scraper;
    private Document doc;

    public CurrencyConvertor(Map currencies, IScraper scraper) throws
    ParsingException,
    InvalidPathException,
    SecurityException,
    IOException {
        this.currencies = currencies;
        this.scraper = scraper;

        Path path = FileSystems
            .getDefault()
            .getPath("Currencies.xml");

        Builder builder = new Builder();
        this.doc = builder.build(Files.newBufferedReader(path));
    }

    public double convert(String src, String dest, double mnt_to_cnvrt) {
        return 2 * mnt_to_cnvrt;
    }

    public void convertAll() {}

    public boolean isValidCurrency(String code) {
        if (this.currencies.containsKey(code)) return true;
        return false;
    }

    public String getCurrCodeByCountry(String target_country) {
        Elements elements;
        Element element;
        String current_country;
        String target_country_code = null;
        element = this.doc.getRootElement();
        elements = element.getChildElements("Currency");

        for (int i = 0; i < elements.size(); i++) {
            current_country = elements
                .get(i)
                .getFirstChildElement("Country")
                .getValue();

            if (target_country.equalsIgnoreCase(current_country)) {
                target_country_code = elements
                    .get(i)
                    .getFirstChildElement("Code")
                    .getValue();
                break;
            }
        }

        return target_country_code;
    }

    public String getCurrCodeByName(String target_curr) {
        Elements elements;
        Element element;
        String current_curr;
        String target_curr_code = null;
        element = this.doc.getRootElement();
        elements = element.getChildElements("Currency");

        for (int i = 0; i < elements.size(); i++) {
            current_curr = elements
                .get(i)
                .getFirstChildElement("Name")
                .getValue();

            if (target_curr.equalsIgnoreCase(current_curr)) {
                target_curr_code = elements
                    .get(i)
                    .getFirstChildElement("Code")
                    .getValue();
                break;
            }
        }

        return target_curr_code;
    }

    public void getInfo() {}
    public void listCurrencies() {}
    public void setBaseCurrency() {}
    public void getBaseCurrency() {}
    public void isBaseCurrencySet() {}
}
