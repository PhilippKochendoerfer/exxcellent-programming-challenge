package de.exxcellent.challenge;

import java.io.IOException;
import java.io.InputStream;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataReader;
import de.exxcellent.challenge.data.DataReaderFactory;
import de.exxcellent.challenge.data.DataSourceFactory;
import de.exxcellent.challenge.exception.InvalidDataException;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;

/**
 * The main class for the programming challenge. It contains the entry point and the main logic for processing the data files.
 */
public class Challenge {

    /**
     * Processes the data based on the specified application parameter (either weather or football) and returns the result.
     * @param app The application parameter indicating which analysis to perform (--weather or --football).
     * @param format The format of the data (--csv or --json).
     * @param sourceType The type of source to read from (--file or --url).
     * @param location The file path or URL to read the data from.
     * @return A message describing the result of the requested computation.
     * @throws IOException if the data cannot be read
     * @throws InvalidDataException if the data contains no row usable for the requested computation
     */
    public static String challenge(String app, String format, String sourceType, String location)
            throws IOException, InvalidDataException {

        try (InputStream inputStream = DataSourceFactory.open(sourceType, location);
                DataReader dataReader = DataReaderFactory.create(format, inputStream)) {
            Data data = dataReader.readData();

            switch (app) {
                case "--weather":
                    String dayWithSmallestTempSpread = Weather.getDayWithSmallestTemperatureSpread(data);
                    return String.format("Day with smallest temperature spread : %s", dayWithSmallestTempSpread);

                case "--football":
                    String teamWithSmallestGoalSpread = Football.getTeamWithSmallestDiff(data);
                    return String.format("Team with smallest goal spread : %s", teamWithSmallestGoalSpread);
                default:
                    throw new IllegalArgumentException("Invalid app parameter. Use --weather or --football.");
            }
        }
    }


}
