package de.exxcellent.challenge;

import java.io.IOException;

import de.exxcellent.challenge.exception.NoDataFoundException;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software design. Read: create your own
 * classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        if (args.length != 2) {
            System.err.println("Usage: <--weather|--football> <file>");
            System.exit(1);
        }

        String appParam = args[0];
        String fileParam = args[1];
        try {
            Challenge.challenge(appParam, fileParam);
        } catch (IOException e) {
            System.err.println("I/O error while reading file: " + e.getMessage());
            System.exit(1);
        } catch (NoDataFoundException e) {
            System.err.println("Could not calculate result. " + e.getMessage());
            System.exit(1);
        }

    }
}
