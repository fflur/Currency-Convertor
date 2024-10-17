package org.cli;

import com.github.rvesse.airline.help.Help;
import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.annotations.Group;
import com.github.rvesse.airline.annotations.Groups;
import com.github.rvesse.airline.annotations.AirlineModule;

@Cli (
    name = "ccvertor",
    description = "The ultimate \"blazing fast\" currency convertor.",
    defaultCommand = Help.class,
    commands = {
        Convert.class,
        ListCurrencies.class,
        IsValid.class
    }
)
public class UserInterface {
    public static void main(String[] args) {
        com.github.rvesse.airline.Cli<Runnable> cli =
            new com.github.rvesse.airline.Cli<Runnable>(UserInterface.class);

        Runnable ccvertor = cli.parse(args);
        ccvertor.run();
    }
}
