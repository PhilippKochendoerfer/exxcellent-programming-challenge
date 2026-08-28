package de.exxcellent.challenge.weather;

import java.io.IOException;

import de.exxcellent.challenge.calc.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;


/**
 * Provides weather-specific analysis over {@link Data} read from a CSV file.
 */
public class Weather {
    /**
     * Returns the day with the smallest difference between maximum and minimum temperature.
     * @param data The data to analyze.
     * @return The day with the smallest temperature spread.
     * @throws NoDataFoundException if the data contains no row usable for the computation
     */
    public static String getDayWithSmallestTemperatureSpread(Data data) throws NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);
    }
}
