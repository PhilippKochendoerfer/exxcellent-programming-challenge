package de.exxcellent.challenge.weather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Weather {

    public static String getDayWithSmallestTemperatureSpread(String filePath) {
        String dayWithSmallestSpread = "No Day Found";
        int smallestSpread = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String day = parts[0];
                    int maxTemp = Integer.parseInt(parts[1]);
                    int minTemp = Integer.parseInt(parts[2]);
                    int spread = maxTemp - minTemp;

                    if (spread < smallestSpread) {
                        smallestSpread = spread;
                        dayWithSmallestSpread = day;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return dayWithSmallestSpread;

    }
}
