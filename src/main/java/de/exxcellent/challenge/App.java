package de.exxcellent.challenge;

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

        try {
            String dayWithSmallestTempSpread = Weather.getDayWithSmallestTemperatureSpread("src/main/resources/de/exxcellent/challenge/weather.csv");
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        } catch (RuntimeException e) {
            System.err.println("Error reading weather data: " + e.getMessage());
            System.exit(1);
        }
        
        // String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        // System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
