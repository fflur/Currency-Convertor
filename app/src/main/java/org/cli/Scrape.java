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
import java.nio.file.FileAlreadyExistsException;
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
            System.out.println(
                "Error! " +
                "Data not found. " +
                "Issue \"scraper\" command."
            );
        }

        catch (NotSerializableException e) {
            System.out.println(
                "Error! " +
                "Incompatible data. " +
                "Data might have been tampered. " +
                "Issue \"scraper\" command"
            );
        }

        catch(MalformedURLException e) {
            System.out.println("Error! Unparseable link or illegal protocol.");
        }

        catch(HttpStatusException e) {
            System.out.println("Error! HTTP returned a \"NOT OK\" response.");
        }

        catch(UnsupportedMimeTypeException e) {
            System.out.println("Error! Unsupported MIME.");
        }

        catch(SocketTimeoutException e) {
            System.out.println("Error! Took longer than expected.");
        }

        catch(NullPointerException e) {
            System.out.println("Error! Saving failed. File doesn't exist.");
        }

        catch(FileAlreadyExistsException e) {
            System.out.println("Error! Trying to access existing file.");
        }

        catch(SecurityException e) {
            System.out.println("Fatal Error! Security violation.");
        }

        catch(UnsupportedOperationException e) {
            System.out.println("Error! Requested operation is unsupported.");
        }

        catch (IOException e) {
            System.out.println("Error! Failed or interrupted IO operation.");
        }
    }
}
