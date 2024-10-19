package org.cli;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;

@Command (
    name = "version",
    description = "To show version and author."
)
public class Version implements Runnable {
    private HelpOption<UserInterface> help;

    @Override
    public void run() {
        System.out.println("Version : 1.0.0");
        System.out.println(
            "Running on : " +
            System.getProperty("os.name")
        );
        System.out.println("Author : Broly - The Saiyajin");
    }
}
