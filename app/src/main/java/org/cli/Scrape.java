package org.cli;

import org.scrapers.DefaultScraper;
import org.core.CurrencyConvertor;
import org.database.DataManager;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;

import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.InvalidClassException;
import java.io.IOException;

@Command (
    name = "scrape",
    description = "Runs the scraper."
)
public class Scrape implements Runnable {
    private HelpOption<UserInterface> help;

    @Override
    public void run() {
        try {
            DefaultScraper scraper = new DefaultScraper();
            scraper.scrape();
            scraper.produceCurrencies();

            CurrencyConvertor ctor = CurrencyConvertor.getInstance(
                scraper.getData()
            );

            DataManager mgr = DataManager.getInstance();
            mgr.ensureFiles();
            ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(mgr.getCurrenciesFile())
            );

            oos.writeObject(ctor);
            oos.flush();
            oos.close();
        }

        catch (InvalidClassException e) {
            System.out.println(e.getMessage());
        }

        catch (NotSerializableException e) {
            System.out.println(e.getMessage());
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // public void test() throws
    //     MalformedURLException,
    //     HttpStatusException,
    //     UnsupportedMimeTypeException,
    //     SocketTimeoutException,
    //     IOException
    // {
    //     DefaultScraper scraper = new DefaultScraper();
    //     System.out.println("Scraper instance created. Yay!");
    // }
}
