package de.exxcellent.challenge;

import java.io.IOException;

import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software design. Read: create your own
 * classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private static final String DEFAULT_WEATHER_FILE = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private static final String DEFAULT_FOOTBALL_FILE = "src/main/resources/de/exxcellent/challenge/football.csv";

    /**
     * This is the main entry method of your program. Called without arguments,
     * it runs both the weather and football analysis on the bundled sample data.
     *
     * @param args The CLI arguments passed: none, or exactly
     *             {@code <--weather|--football> <--csv|--json> <--file|--url> <location>}.
     */
    public static void main(String... args) {

        try {
            if (args.length == 0) {
                System.out.println(Challenge.challenge("--weather", "--csv", "--file", DEFAULT_WEATHER_FILE));
                System.out.println(Challenge.challenge("--football", "--csv", "--file", DEFAULT_FOOTBALL_FILE));
            } else if (args.length == 4) {
                System.out.println(Challenge.challenge(args[0], args[1], args[2], args[3]));
            } else {
                System.err.println("Usage: <--weather|--football> <--csv|--json> <--file|--url> <location>");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("I/O error while reading file: " + e.getMessage());
            System.exit(1);
        } catch (InvalidDataException e) {
            System.err.println("Could not calculate result. " + e.getMessage());
            System.exit(1);
        }
    }
}
