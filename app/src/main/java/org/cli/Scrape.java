package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;

@Command (
    name = "scrape",
    description = "Runs the scraper."
)
public class Scrape implements Runnable {
    HelpOption<UserInterface> help;

    @Override
    public void run() {
        System.out.println("Hello! I am scraper.");
    }
}
