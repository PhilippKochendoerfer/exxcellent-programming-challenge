package de.exxcellent.challenge.weather;

import java.io.IOException;
import de.exxcellent.challenge.MinDiff;
import de.exxcellent.challenge.exception.NoDataFoundException;


public class Weather {

    public static String getDayWithSmallestTemperatureSpread(String filePath) throws IOException, NoDataFoundException {
        String day = MinDiff.getKeyWithSmallestDiff(filePath, 0, 1, 2);
        return day;
    }
}
