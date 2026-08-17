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
    public static void main(String... args){

        try {
            String dayWithSmallestTempSpread = Weather
                    .getDayWithSmallestTemperatureSpread("src/main/resources/de/exxcellent/challenge/weather.csv");
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        } catch (IOException e) {
            System.err.println("I/O error while reading weather file: " + e.getMessage());
        } catch (NoDataFoundException e) {
            System.err.println("Could not calculate smallest temperature spread. " + e.getMessage());
        }

        try {
            String teamWithSmallestGoalSpread = Football
                    .getTeamWithSmallestDiff("src/main/resources/de/exxcellent/challenge/football.csv");
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        } catch (IOException e) {
            System.err.println("Error reading football file: " + e.getMessage());
            System.exit(1);
        } catch (NoDataFoundException e) {
            System.err.println("Could not calculate smallest goal spread. " + e.getMessage());
        }
    }
}
