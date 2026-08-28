package de.exxcellent.challenge.weather;

import java.io.IOException;

import de.exxcellent.challenge.calc.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;


public class Weather {
    /**
     * Returns the day with the smallest difference between maximum and minimum temperature.
     * @param data The data to analyze.
     * @return The day with the smallest temperature spread.
     * @throws NoDataFoundException
     */
    public static String getDayWithSmallestTemperatureSpread(Data data) throws NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);
    }
}
