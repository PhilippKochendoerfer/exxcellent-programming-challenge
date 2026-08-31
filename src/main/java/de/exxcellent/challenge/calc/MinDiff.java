package de.exxcellent.challenge.calc;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Generic helper for finding the row with the smallest difference between two
 * columns in a {@link Data} table.
 */
public class MinDiff {
    /**
     * Returns the key with the smallest difference between the values in the
     * specified minuend and subtrahend columns.
     *
     * @param data             The data to analyze.
     * @param keyColumn        Name of the column identifying each row.
     * @param minuendColumn    Name of the minuend column.
     * @param subtrahendColumn Name of the subtrahend column.
     * @param absolute         Flag indicating whether to consider the absolute
     *                         difference or not.
     * @return The key with the smallest difference.
     * @throws InvalidDataException if the data contains no rows, any of the given
     *                              column names does not exist in the header, or a
     *                              row's minuend/subtrahend value is not a valid integer
     */
    public static String getKeyWithSmallestDiff(Data data, String keyColumn, String minuendColumn,
            String subtrahendColumn, boolean absolute) throws InvalidDataException {

        String keyWithSmallestSpread = null;
        int smallestSpread = Integer.MAX_VALUE;

        for (Data.Row row : data) {
            String key = row.getValue(keyColumn);
            int minuend;
            int subtrahend;
            try {
                minuend = Integer.parseInt(row.getValue(minuendColumn));
            } catch (NumberFormatException e) {
                throw new InvalidDataException(
                        "Row for key '" + key + "' has a non-numeric value in column '" + minuendColumn + "'.");
            }
            try {
                subtrahend = Integer.parseInt(row.getValue(subtrahendColumn));
            } catch (NumberFormatException e) {
                throw new InvalidDataException(
                        "Row for key '" + key + "' has a non-numeric value in column '" + subtrahendColumn + "'.");
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
