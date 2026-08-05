package de.exxcellent.challenge.weather;

import java.io.IOException;
import de.exxcellent.challenge.MinDiff;


public class Weather {

    public static String getDayWithSmallestTemperatureSpread(String filePath) {
        String day = MinDiff.getKeyWithSmallestDiff(filePath, 0, 1, 2);
        if (day == null) {
            throw new RuntimeException("No day found with valid temperature data");
        }
        return day;
    }
}
