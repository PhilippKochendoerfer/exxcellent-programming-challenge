
package de.exxcellent.challenge.data;

import java.util.List;
/**
 * Class to represent table-like data structure with header and rows.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class Data {
    private final String[] header;
    private final List<String[]> rows;

    /**
     * Creates a new Data instance, validating that every row has the same
     * number of columns as the header.
     *
     * @param header The column names.
     * @param rows   The data rows; each row's length must match {@code header}'s length.
     * @throws IllegalArgumentException if header or rows is null, or if any row's
     *                                  length differs from the header's length.
     */
    public Data(String[] header, List<String[]> rows) {
        if (header == null || rows == null) {
            throw new IllegalArgumentException("Header and rows cannot be null");
        }
        if (rows.stream().anyMatch(row -> row.length != header.length)) {
            throw new IllegalArgumentException("All rows must have the same number of columns as the header");
        }

        this.header = header;
        this.rows = rows;
    }

    public String[] getHeader() {
        return header;
    }
    public List<String[]> getRows() {
        return rows;
    }
}