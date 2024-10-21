# ABOUT THIS PROJECT:
Just a hobby project where I tried to use all my knowledge of programming
by using Gradle, Java, Web Scraping, Java's Collection framework, OO concepts
and a little knowledge of Software Patterns. I tried to make the design such so
that a GUI or a TUI can be implemented easily by modifying the source code.
Moreover, the source is easily modifiable to include as many Web Scrapers
needed or wanted. Check out the "WHAT'S EASILY MODIFIABLE?" section. Fork the
repository and do whatever you want with it.

# SETTING THIS PROJECT
- No prerequisites!
- Clone this repository.
- Run `./gradlew` (on linux based OS) or `.\gradlew` (on Windows OS).
- Done!

# INSTALLING AS APPLICATION
## Prerequisites for running:
- Java Runtime Environment (JRE) 17+ needed.

## Instructions for running:
- Application is available in both JAR file and ZIP file in the releases section.
- Run JAR file by issuing `java -jar ccvertor.jar [args]`.
- Or after extracting ZIP file, add the `bin` directory's location in the `PATH`
environment variable on both Windows OS and Linux based OS, basically installing
the application system wide.
- Then run `ccvertor` to run the command from any directory.
- If you don't want to add the `bin` directory to `PATH` environment variable,
run the application from the `bin` directory (everytime) by issuing `./ccvertor`
(on linux based OS) or `.\ccvertor` (on Windows OS).

# WHAT'S EASILY MODIFIABLE?
### For adding a UI:
Your UI will reside in a separate package. After implementing the UI just modify
the `build.gradle.kts` such that it points to the class which contains the
`main` method. You can implement any GUI, TUI, or CLI.

### For adding a web scraper:
Your webscraper just needs to implement the `IScraper` interface and pass the
value returned by `getData()` method as parameter when creating an instance of
`CurrencyConvertor`.
