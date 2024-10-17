package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.restrictions.Required;
import com.github.rvesse.airline.annotations.restrictions.MinLength;
import com.github.rvesse.airline.annotations.restrictions.MaxLength;

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
    public String from;

    @Option (
        name = {"--to", "-t"},
        arity = 1,
        description = "The target currency code."
    )
    @MinLength ( length = 3 )
    @MaxLength ( length = 3 )
    @Required
    public String to;

    @Option (
        name = {"--amount", "-a"},
        arity = 1,
        description = "The amount to convert to target currency."
    )
    @Required
    public double amount;

    @Override
    public void run() {
        System.out.println("Hello, World!");
    }
}
