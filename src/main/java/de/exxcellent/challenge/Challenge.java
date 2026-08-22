package de.exxcellent.challenge;

import java.io.IOException;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;

public class Challenge {
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
