package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.restrictions.Required;
import com.github.rvesse.airline.annotations.restrictions.MinLength;
import com.github.rvesse.airline.annotations.restrictions.MaxLength;

import java.nio.file.Files;
import org.core.CurrencyConvertor;
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
    @MinLength ( length = 3 )
    @MaxLength ( length = 3 )
    @Required
    private String from;

    @Option (
        name = {"--to", "-t"},
        arity = 1,
        description = "The target currency code."
    )
    @MinLength ( length = 3 )
    @MaxLength ( length = 3 )
    @Required
    private String to;

    @Option (
        name = {"--amount", "-a"},
        arity = 1,
        description = "The amount to convert to target currency."
    )
    @Required
    double amount;

    @Override
    public void run() {
        try {
            DataManager mgr = DataManager.getInstance();

            if (!Files.exists(mgr.getCurrenciesFile())) {
                System.out.println("Error! Data files not found. Run the scraper first.");
                return;
            }

            CurrencyConvertor cc = CurrencyConvertor.load();
            double result = cc.convert(this.from, this.to, this.amount);
            System.out.printf(
                "%.2f %s = %.2f %s\n",
                this.amount,
                this.from,
                result,
                this.to
            );
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
