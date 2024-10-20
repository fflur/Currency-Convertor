package org.cli;

import com.github.rvesse.airline.help.Help;
import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.annotations.AirlineModule;
import com.github.rvesse.airline.parser.errors.ParseArgumentsMissingException;
import com.github.rvesse.airline.parser.errors.ParseArgumentsUnexpectedException;
import com.github.rvesse.airline.parser.errors.ParseCommandMissingException;
import com.github.rvesse.airline.parser.errors.ParseCommandUnrecognizedException;
import com.github.rvesse.airline.parser.errors.ParseOptionMissingException;
import com.github.rvesse.airline.parser.errors.ParseOptionMissingValueException;
import com.github.rvesse.airline.parser.errors.ParseOptionUnexpectedException;
import com.github.rvesse.airline.parser.errors.ParseOptionGroupException;
import com.github.rvesse.airline.parser.errors.ParseTooManyArgumentsException;
import com.github.rvesse.airline.parser.errors.ParseRestrictionViolatedException;

@Cli (
    name = "ccvertor",
    description = "The ultimate \"blazing fast\" currency convertor.",
    defaultCommand = Help.class,
    commands = {
        Convert.class,
        ListCurrencies.class,
        IsValid.class,
        Scrape.class,
        Version.class,
        Help.class
    }
)
public class UserInterface {
    public static void main(String[] args) {
        try {
            com.github.rvesse.airline.Cli<Runnable> cli =
                new com.github.rvesse.airline.Cli<Runnable>(
                    UserInterface.class
                );

            Runnable ccvertor = cli.parse(args);
            ccvertor.run();
        }

        catch (ParseRestrictionViolatedException e) {
            System.out.println(e.getMessage());
        }
    }
}
