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
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
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

    public boolean isValidCurrency(String code) {
        if (this.currencies.containsKey(code)) return true;
        return false;
    }

    public String search(String target_country)
    throws XMLException, ParsingException, ValidityException, IOException {
        Elements elements;
        Element element;
        String current_country;
        String target_country_code = null;

        Path path = FileSystems
            .getDefault()
            .getPath("Currencies.xml");

        Builder builder = new Builder();
        Document doc = builder.build(Files.newBufferedReader(path));
        element = doc.getRootElement();
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

    public void getInfo() {}
    public void listCurrencies() {}
    public void setBaseCurrency() {}
    public void getBaseCurrency() {}
    public void isBaseCurrencySet() {}
}
