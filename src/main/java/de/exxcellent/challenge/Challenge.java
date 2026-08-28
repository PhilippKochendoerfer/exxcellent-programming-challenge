package de.exxcellent.challenge;

import java.io.IOException;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;

/**
 * The main class for the programming challenge. It contains the entry point and the main logic for processing the data files.
 */
public class Challenge {

    /**
     * Processes the data file based on the specified application parameter (either weather or football) and prints the result.
     * @param app The application parameter indicating which analysis to perform (--weather or --football).
     * @param file The path to the data file to be processed.
     * @throws IOException if the data file cannot be read
     * @throws NoDataFoundException if the data contains no row usable for the requested computation
     */
    public static void challenge(String app, String file) throws IOException, NoDataFoundException {

        try (CsvReader csvReader = new CsvReader(file)) {
            Data data = csvReader.readData();

            switch (app) {
                case "--weather":
                    String dayWithSmallestTempSpread = Weather.getDayWithSmallestTemperatureSpread(data);
                    System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
                    break;

                case "--football":
                    String teamWithSmallestGoalSpread = Football.getTeamWithSmallestDiff(data);
                    System.out.printf("Team with smallest goal spread : %s%n", teamWithSmallestGoalSpread);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid app parameter. Use --weather or --football.");
            }
        }
    }


}
