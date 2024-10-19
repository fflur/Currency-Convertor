package org.core;

import java.io.IOException;
import java.io.File;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.NotSerializableException;
import java.io.InvalidClassException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.core.UnknownCurrencyException;
import org.scrapers.Currency;
import org.scrapers.IScraper;
import org.database.DataManager;

public final class CurrencyConvertor implements Serializable {
    private static volatile CurrencyConvertor ccvertor;
    private HashMap<String, Currency> currencies;
    public String base_currency;

    private CurrencyConvertor(HashMap currencies) {
        this.currencies = currencies;
    }

    public static CurrencyConvertor getInstance(
        HashMap<String, Currency> currencies
    ) {
        CurrencyConvertor tmp = ccvertor;
        if (tmp != null) return tmp;

        synchronized(CurrencyConvertor.class) {
            if (ccvertor == null)
                ccvertor = new CurrencyConvertor(currencies);
            return ccvertor;
        }
    }

    public static synchronized CurrencyConvertor load() throws
        IOException,
        ClassNotFoundException
    {
        DataManager dm = DataManager.getInstance();
        ObjectInputStream ois = new ObjectInputStream(
            Files.newInputStream(dm.getCurrenciesFile())
        );
        ccvertor = (CurrencyConvertor) ois.readObject();
        return ccvertor;
    }

    public double convert(String src, String dst, double mnt_to_cnvrt) throws
        UnknownCurrencyException
    {
        if (!this.currencies.containsKey(src))
            throw new UnknownCurrencyException(src);
        if (!this.currencies.containsKey(dst))
            throw new UnknownCurrencyException(dst);

        Currency curr_src = (Currency) this.currencies.get(src);
        Currency curr_dst = (Currency) this.currencies.get(dst);

        return (mnt_to_cnvrt / curr_src.getExchangeRate())
            * curr_dst.getExchangeRate();
    }

    public boolean isValidCurrency(String code) {
        return this.currencies.containsKey(code);
    }

    public ArrayList<Currency> getKnownCurrencies() {
        ArrayList<Currency> list_of_curr = new ArrayList<Currency>();

        for (Map.Entry<String, Currency> entry : this.currencies.entrySet())
            list_of_curr.add(entry.getValue());

        return list_of_curr;
    }
}
