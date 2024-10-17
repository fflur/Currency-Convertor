package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;
import com.github.rvesse.airline.annotations.restrictions.MinLength;
import com.github.rvesse.airline.annotations.restrictions.MaxLength;

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
        System.out.println("Hello! I am validater.");
    }
}
