package de.exxcellent.challenge;

import java.io.IOException;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataFileReader;

public class MinDiff {

    public static String getKeyWithSmallestDiff(String filePath, int keyIndex, int minuendIndex, int subtrahendIndex) {
        return MinDiff.getKeyWithSmallestDiff(filePath, keyIndex, minuendIndex, subtrahendIndex, false);
    }

    public static String getKeyWithSmallestDiff(String filePath, int keyIndex, int minuendIndex, int subtrahendIndex, boolean absolute) {
        Data weatherData = getDataFromFile(filePath);
        return getKeyWithSmallestDiff(weatherData, keyIndex, minuendIndex, subtrahendIndex, absolute);

    }

    private static Data getDataFromFile(String filePath) {
        DataFileReader csvReader = new CsvReader(filePath);
        try {
            return csvReader.readData();
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + e.getMessage());
        }
    }

    private static String getKeyWithSmallestDiff(Data data, int keyIndex, int minuendIndex, int subtrahendIndex, boolean absolute) {
        String keyWithSmallestSpread = null;
        int smallestSpread = Integer.MAX_VALUE;
        int requiredColumns = Math.max(keyIndex, Math.max(minuendIndex, subtrahendIndex)) + 1;

        for (String[] row : data.getRows()) {
            if (row.length >= requiredColumns) {
                String key = row[keyIndex];
                int minuend = Integer.parseInt(row[minuendIndex]);
                int subtrahend = Integer.parseInt(row[subtrahendIndex]);
                int spread = minuend - subtrahend;
                if (absolute) {
                    spread = Math.abs(spread);
                }

                if (spread < smallestSpread) {
                    smallestSpread = spread;
                    keyWithSmallestSpread = key;
                }
            }
        }
        return keyWithSmallestSpread;
    }
}
