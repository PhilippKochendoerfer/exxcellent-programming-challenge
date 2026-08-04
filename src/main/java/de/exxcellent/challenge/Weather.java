package de.exxcellent.challenge;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.exxcellent.challenge.util.CsvReader;
import de.exxcellent.challenge.util.Data;

public class Weather {

    private static final String FILE_PATH = "src/main/resources/de/exxcellent/challenge/weather.csv";

    /**
     * This method reads the weather data from a CSV file and finds the day with
     * the smallest temperature spread (difference between maximum and minimum
     * temperature).
     */
    public static String getDayWithSmallestTemperatureSpread() {
        int smallestSpread = Integer.MAX_VALUE;
        int dayWithSmallestSpread = -1;


        try {
            CsvReader reader = new CsvReader(FILE_PATH, ",");
            Data data = reader.readData();
            
            for (String[] line : data.getRows()) {
                int[] spreadData = getTemperatureSpread(line);
                int day = spreadData[0];
                int spread = spreadData[1];

                if (spread < smallestSpread) {
                    smallestSpread = spread;
                    dayWithSmallestSpread = day;
                }
            }

        } catch (FileNotFoundException ex) {
            System.getLogger(Weather.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(Weather.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return dayWithSmallestSpread + "";
    }

    private static int[] getTemperatureSpread(String[] line) {
        int day = Integer.parseInt(line[0]);
        int maxTemp = Integer.parseInt(line[1]);
        int minTemp = Integer.parseInt(line[2]);
        int spread = maxTemp - minTemp;
        return new int[]{day, spread};
    }
}
