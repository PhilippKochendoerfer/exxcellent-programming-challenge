package de.exxcellent.challenge.weather;

import de.exxcellent.challenge.calc.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.InvalidDataException;


/**
 * Provides weather-specific analysis over {@link Data}.
 */
public class Weather {
    /**
     * Returns the day with the smallest difference between maximum and minimum temperature.
     * @param data The data to analyze.
     * @return The day with the smallest temperature spread.
     * @throws InvalidDataException if the data contains no row usable for the computation
     */
    public static String getDayWithSmallestTemperatureSpread(Data data) throws InvalidDataException {
        return MinDiff.getKeyWithSmallestDiff(data, "Day", "MxT", "MnT", false);
    }
}
