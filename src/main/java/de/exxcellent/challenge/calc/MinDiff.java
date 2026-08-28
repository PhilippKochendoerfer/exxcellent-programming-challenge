package de.exxcellent.challenge.calc;

import java.util.List;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;

/**
 * Generic helper for finding the row with the smallest difference between two
 * columns in a {@link Data} table.
 */
public class MinDiff {
    /**
     * Returns the key with the smallest difference between the values at the
     * specified minuend and subtrahend indices.
     *
     * @param data            The data to analyze.
     * @param keyIndex        Index of the key in the row.
     * @param minuendIndex    Index of the minuend value in the row.
     * @param subtrahendIndex Index of the subtrahend value in the row.
     * @param absolute        Flag indicating whether to consider the absolute
     *                        difference or not.
     * @return The key with the smallest difference.
     * @throws NoDataFoundException if the data contains no row usable for the computation
     */
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
