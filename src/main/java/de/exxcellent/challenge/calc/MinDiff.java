package de.exxcellent.challenge.calc;

import java.util.Arrays;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.InvalidDataException;

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
     * @throws IllegalArgumentException if any index is negative.
     * @throws InvalidDataException     if the data contains no rows, the header has fewer
     *                                   columns than required, or a row's minuend/subtrahend
     *                                   value is not a valid integer
     */
    public static String getKeyWithSmallestDiff(Data data, int keyIndex, int minuendIndex, int subtrahendIndex,
            boolean absolute) throws InvalidDataException {
        if (keyIndex < 0 || minuendIndex < 0 || subtrahendIndex < 0) {
            throw new IllegalArgumentException("Column indices must not be negative.");
        }

        int requiredColumns = Math.max(keyIndex, Math.max(minuendIndex, subtrahendIndex)) + 1;
        if (data.getHeader().length < requiredColumns) {
            throw new InvalidDataException(
                    "Header has only " + data.getHeader().length + " column(s), but at least "
                            + requiredColumns + " are required.");
        }

        String keyWithSmallestSpread = null;
        int smallestSpread = Integer.MAX_VALUE;

        for (String[] row : data.getRows()) {
            String key = row[keyIndex];
            int minuend;
            int subtrahend;
            try {
                minuend = Integer.parseInt(row[minuendIndex]);
                subtrahend = Integer.parseInt(row[subtrahendIndex]);
            } catch (NumberFormatException e) {
                throw new InvalidDataException(
                        "Row for key '" + key + "' contains a non-numeric value: " + Arrays.toString(row));
            }
            int spread = minuend - subtrahend;
            if (absolute) {
                spread = Math.abs(spread);
            }

            if (spread < smallestSpread) {
                smallestSpread = spread;
                keyWithSmallestSpread = key;
            }
        }

        if (keyWithSmallestSpread == null) {
            throw new InvalidDataException("No rows found.");
        }

        return keyWithSmallestSpread;
    }
}
