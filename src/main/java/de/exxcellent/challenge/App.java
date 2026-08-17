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
     * @throws NoDataFoundException
     */
    public static void main(String... args) {

        if (args.length != 2) {
            System.err.println("Usage: <--weather|--football> <file>");
            return;
        }

        String appParam = args[0];
        String fileParam = args[1];
        try {
            switch (appParam) {
                case "--weather":
                    String dayWithSmallestTempSpread = Weather
                            .getDayWithSmallestTemperatureSpread(
                                    fileParam);
                    System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

                    break;

                case "--football":
                    String teamWithSmallestGoalSpread = Football
                            .getTeamWithSmallestDiff(fileParam);
                    System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
                    break;

                default:
                    System.err.println("Unknown option: " + appParam);
                    break;
            }

        } catch (IOException e) {
            System.err.println("I/O error while reading file: " + e.getMessage());
        } catch (NoDataFoundException e) {
            System.err.println("Could not calculate smallest temperature spread. " + e.getMessage());
        }

    }
}
