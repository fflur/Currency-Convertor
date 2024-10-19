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
        } catch (ParseArgumentsMissingException e) {
            System.out.println("Error: Missing arguments!");
        } catch (ParseCommandMissingException e) {
            System.out.println("Error: Specify a command!");
        } catch (ParseCommandUnrecognizedException e) {
            System.out.println("Error: Unrecognized command!");
        } catch (ParseArgumentsUnexpectedException e) {
            System.out.println("Error: Unexpected arguments!");
        } catch (ParseOptionMissingException e) {
            System.out.println("Error: Arguments missing!");
        } catch (ParseOptionMissingValueException e) {
            System.out.println("Error! Missing values!");
        } catch (ParseOptionUnexpectedException e) {
            System.out.println("Error! Illegal option position!");
        } catch (ParseOptionGroupException e) {
            System.out.println("Error! Specify atleast one arguments!");
        } catch (ParseTooManyArgumentsException e) {
            System.out.println("Error! Too many arguments!");
        }
    }
}
