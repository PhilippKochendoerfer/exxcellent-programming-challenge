package de.exxcellent.challenge.weather;

import java.io.IOException;
import de.exxcellent.challenge.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;


public class Weather {

    public static String getDayWithSmallestTemperatureSpread(Data data) throws IOException, NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2);
    }
}
