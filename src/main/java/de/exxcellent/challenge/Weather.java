package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Weather {

    private static final String FILE_PATH = "src/main/resources/de/exxcellent/challenge/weather.csv";

    /**
     * This method reads the weather data from a CSV file and finds the day with the smallest temperature spread (difference between maximum and minimum temperature).
     */
    public static String getDayWithSmallestTemperatureSpread() {
        int smallestSpread = Integer.MAX_VALUE;
        int dayWithSmallestSpread = -1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            reader.readLine(); // Skip the header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                int day = Integer.parseInt(parts[0]);
                int maxTemp = Integer.parseInt(parts[1]);
                int minTemp = Integer.parseInt(parts[2]);
                int spread = maxTemp - minTemp;

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
   
}
