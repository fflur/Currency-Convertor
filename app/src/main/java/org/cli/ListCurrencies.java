package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;

@Command (
    name = "list-currencies",
    description = "To list available currencies' code with their name."
)
public class ListCurrencies implements Runnable {
    private HelpOption<UserInterface> help;

    @Override
    public void run() {
        System.out.println("Hello! I am list-currencies command.");
    }
}
