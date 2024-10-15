package org.core;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Serializer;
import nu.xom.XMLException;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nu.xom.Node;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.charset.StandardCharsets;
import org.scrapers.Currency;
import org.scrapers.IScraper;
import org.core.InvalidCurrencyCode;

public class CurrencyConvertor {
    private Map<String, Currency> currencies;
    private IScraper scraper;
    private Document doc;

    public CurrencyConvertor(Map currencies, IScraper scraper) throws
        ParsingException,
        InvalidPathException,
        SecurityException,
        IOException
    {
        this.currencies = currencies;
        this.scraper = scraper;

        Path path = FileSystems
            .getDefault()
            .getPath("Currencies.xml");

        Builder builder = new Builder();
        this.doc = builder.build(Files.newBufferedReader(path));
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

    public String[] listCurrencies() {
        String fmt_crrncs[] = new String[this.currencies.size()];
        Elements elements = this.doc.getRootElement().getChildElements();
        Element element;
        String code, name;

        for (int i = 0; i < elements.size(); i++) {
            element = elements.get(i);
            code = element.getFirstChildElement("Code").getValue();
            name = element.getFirstChildElement("Name").getValue();
            fmt_crrncs[i] = code + " - " + name;
        }

        return fmt_crrncs;
    }

    public void setBaseCurrency(String code) throws
        ParsingException,
        IOException,
        InvalidCurrencyCode
    {
        if (!this.currencies.containsKey(code)) throw new InvalidCurrencyCode();
        Element element, root_element;
        Elements elements;
        String val;
        Path path = FileSystems
            .getDefault()
            .getPath("config.xml");
        Builder builder = new Builder();
        Document config_doc = builder.build(Files.newBufferedReader(path));
        Serializer srlzr = new Serializer(Files.newOutputStream(path));
        element = config_doc
            .getRootElement()
            .getFirstChildElement("BaseCurrency");
        element.removeChild(0);
        element.appendChild(code);
        srlzr.setIndent(2);
        srlzr.write(config_doc);
        srlzr.flush();
    }

    public String getBaseCurrency() throws
        ParsingException,
        IOException
    {
        Element element, root_element;
        Elements elements;
        String val;
        Path path = FileSystems
            .getDefault()
            .getPath("config.xml");
        Builder builder = new Builder();
        Document config_doc = builder.build(Files.newBufferedReader(path));
        element = config_doc
            .getRootElement()
            .getFirstChildElement("BaseCurrency");

        return element.getValue();
    }

    public boolean isBaseCurrencySet() throws ParsingException, IOException {
        return !this.getBaseCurrency().isEmpty();
    }
}
