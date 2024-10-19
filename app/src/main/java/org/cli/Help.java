package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.Command;

@Command (
    name = "help",
    description = "Shows all available commands"
)
public class Help implements Runnable {
    @Option (
        name = "--true"
    )
    private HelpOption<UserInterface> help = new HelpOption<UserInterface>();

    @Override
    public void run() {
        this.help.showHelp();
    }
}
