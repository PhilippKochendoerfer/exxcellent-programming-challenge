package de.exxcellent.challenge;

import java.io.IOException;
import java.util.List;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;

public class MinDiff {

    public static String getKeyWithSmallestDiff(Data data, int keyIndex, int minuendIndex, int subtrahendIndex)
            throws IOException, NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, keyIndex, minuendIndex, subtrahendIndex, false);
    }

    public static String getKeyWithSmallestDiff(Data data, int keyIndex, int minuendIndex, int subtrahendIndex,
            boolean absolute) throws NoDataFoundException {
        String keyWithSmallestSpread = null;
        int smallestSpread = Integer.MAX_VALUE;
        int requiredColumns = Math.max(keyIndex, Math.max(minuendIndex, subtrahendIndex)) + 1;
        List<String[]> rows = data.getRows();
        if (rows.size() < 1) {
            throw new NoDataFoundException("No Data Found!");
        }
        for (String[] row : rows) {
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
