package de.exxcellent.challenge.weather;

import java.io.IOException;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataFileReader;

public class Weather {

    public static String getDayWithSmallestTemperatureSpread(String filePath) {
        Data weatherData = getDataFromFile(filePath);
        return getDayWithSmallestTemperatureSpread(weatherData);

    }

    private static Data getDataFromFile(String filePath) {
        DataFileReader csvReader = new CsvReader(filePath);
        try {
            return csvReader.readData();
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + e.getMessage());
        }
    }

    private static String getDayWithSmallestTemperatureSpread(Data weatherData) {
        String dayWithSmallestSpread = "No Day Found";
        int smallestSpread = Integer.MAX_VALUE;

        for (String[] row : weatherData.getRows()) {
            if (row.length >= 3) {
                String day = row[0];
                int maxTemp = Integer.parseInt(row[1]);
                int minTemp = Integer.parseInt(row[2]);
                int spread = maxTemp - minTemp;

                if (spread < smallestSpread) {
                    smallestSpread = spread;
                    dayWithSmallestSpread = day;
                }
            }
        }
        return dayWithSmallestSpread;
    }
}
