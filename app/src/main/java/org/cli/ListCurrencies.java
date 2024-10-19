package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;

import org.core.CurrencyConvertor;
import org.scrapers.Currency;
import org.database.DataManager;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;

@Command (
    name = "list-currencies",
    description = "To list available currencies' code with their name."
)
public class ListCurrencies implements Runnable {
    private HelpOption<UserInterface> help;

    @Override
    public void run() {
        try {
            DataManager dm = DataManager.getInstance();

            if (!Files.exists(dm.getCurrenciesFile()))
                System.out.println(
                    "Error! " +
                    "Data files not found. " +
                    "Run the scraper first."
                );

            CurrencyConvertor cc = CurrencyConvertor.load();
            ArrayList<Currency> curr_lst = cc.getKnownCurrencies();

            for (Currency currency : curr_lst)
                System.out.println(
                    currency.getCode() +
                    " : " +
                    currency.getName()
                );
        }

        catch (SecurityException e) {
            System.out.println("Fatal Error! Security violation.");
        }

        catch (ClassNotFoundException e) {
            System.out.println(
                "Error! " +
                "Data not found. " +
                "Issue \"scraper\" command."
            );
        }

        catch (IOException e) {
            System.out.println("Error! Failed or interrupted IO operation.");
        }
    }
}
