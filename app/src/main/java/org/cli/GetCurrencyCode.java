package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.RequireOnlyOne;

@Command (
    name = "get-code",
    description = "Fetches currency code from the datafile."
)
public class GetCurrencyCode implements Runnable {
    private HelpOption<UserInterface> help;
    
    @Option (
        name = {"--country"},
        arity = 1,
        description = "Name of a country."
    )
    @RequireOnlyOne
    private String country;

    @Option (
        name = {"--currency-code"},
        arity = 1,
        description = "Full name of a currency."
    )
    @RequireOnlyOne
    private String name;

    @Override
    public void run() {
        System.out.println("Hello! I am fetcher.");
    }
}
