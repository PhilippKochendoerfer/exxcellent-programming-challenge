package de.exxcellent.challenge;

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

    private static final String DEFAULT_WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private static final String DEFAULT_FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    /**
     * This is the main entry method of your program.
     * Without arguments, both challenges run against their default CSV files.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        if (args.length == 0) {
            runWeather(DEFAULT_WEATHER_CSV);
            runFootball(DEFAULT_FOOTBALL_CSV);
            return;
        }

        if (args.length != 2) {
            System.err.println("Usage: java -cp target/classes de.exxcellent.challenge.App [--weather <weather.csv> | --football <football.csv>]");
            System.exit(1);
        }

        switch (args[0]) {
            case "--weather" -> runWeather(args[1]);
            case "--football" -> runFootball(args[1]);
            default -> {
                System.err.println("Unknown option: " + args[0]);
                System.exit(1);
            }
        }
    }

    private static void runWeather(String csvPath) {
        try {
            String dayWithSmallestTempSpread = Weather.getDayWithSmallestTemperatureSpread(csvPath);
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        } catch (RuntimeException e) {
            System.err.println("Error reading weather data: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void runFootball(String csvPath) {
        try {
            String teamWithSmallestGoalSpread = Football.getTeamWithSmallestDiff(csvPath);
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        } catch (RuntimeException e) {
            System.err.println("Error reading football data: " + e.getMessage());
            System.exit(1);
        }
    }
}
