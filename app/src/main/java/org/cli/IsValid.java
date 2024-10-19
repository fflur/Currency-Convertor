package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;
import com.github.rvesse.airline.annotations.restrictions.MinLength;
import com.github.rvesse.airline.annotations.restrictions.MaxLength;

import org.core.CurrencyConvertor;
import org.database.DataManager;
import java.nio.file.Files;
import java.io.IOException;

@Command (
    name = "validate",
    description = "To check for existence of currency code."
)
public class IsValid implements Runnable {
    private HelpOption<UserInterface> help;

    @Option (
        name = {"--code", "-c"},
        arity = 1,
        description = "Specify currency's code."
    )
    @MinLength ( length = 3 )
    @MaxLength ( length = 3 )
    @Required
    private String curr_code;

    @Override
    public void run() {
        try {
            DataManager dm = DataManager.getInstance();

            if (!Files.exists(dm.getCurrenciesFile()))
                System.out.println(
                    "Error! " +
                    "Data files not found. " +
                    "Run the scraper first"
                );

            CurrencyConvertor cc = CurrencyConvertor.load();

            if (cc.isValidCurrency(this.curr_code))
                System.out.println("Valid");
            else
                System.out.println("Invalid");
        }

        catch (SecurityException e) {
            System.out.println("Fatal Error! Security violation.");
        }

        catch (IOException e) {
            System.out.println("Error! Failed or interrupted IO operation.");
        }

        catch (ClassNotFoundException e) {
            System.out.println(
                "Error! " +
                "Data not found. " +
                "Issue \"scraper\" command."
            );
        }
    }
}
