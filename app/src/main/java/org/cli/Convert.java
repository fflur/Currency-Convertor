package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.restrictions.Required;
import com.github.rvesse.airline.annotations.restrictions.ExactLength;

import java.nio.file.Files;
import java.io.IOException;
import org.core.CurrencyConvertor;
import org.core.UnknownCurrencyException;
import org.database.DataManager;

@Command (
    name = "convert",
    description =
        "To convert between any two currencies using the" +
        " respective currency codes."
)
public class Convert implements Runnable {
    private HelpOption<UserInterface> help;

    @Option (
        name = {"--from", "-f"},
        arity = 1,
        description =
            "The currency code from which the amount will" +
            " be converted."
    )
    @ExactLength ( length = 3 )
    @Required
    private String from;

    @Option (
        name = {"--to", "-t"},
        arity = 1,
        description = "The target currency code."
    )
    @ExactLength ( length = 3 )
    @Required
    private String to;

    @Option (
        name = {"--amount", "-a"},
        arity = 1,
        description = "The amount to convert to target currency."
    )
    @Required
    private String amt;

    @Override
    public void run() {
        try {
            double amount = Double.parseDouble(this.amt);
            DataManager mgr = DataManager.getInstance();

            if (!Files.exists(mgr.getCurrenciesFile())) {
                System.out.println(
                    "Error! " +
                    "Data files not found. " +
                    "Run the scraper first."
                );
                return;
            }

            CurrencyConvertor cc = CurrencyConvertor.load();
            double result = cc.convert(this.from, this.to, amount);
            System.out.printf(
                "%.2f %s = %.2f %s\n",
                amount,
                this.from,
                result,
                this.to
            );
        }

        catch (NumberFormatException e) {
            System.out.println("Error! Invalid amount.");
        }

        catch(UnknownCurrencyException e) {
            System.out.println(
                "Error! " +
                "Illegal currency code or unrecognized code. " +
                "Run \"list-currencies\" to see list of " +
                "currency codes recognized by this program."
            );
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
