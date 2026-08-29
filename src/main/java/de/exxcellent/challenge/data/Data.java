
package de.exxcellent.challenge.data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Immutable class to represent table-like data structure with header and rows.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public final class Data {
    private final String[] header;
    private final List<String[]> rows;

    /**
     * Creates a new Data instance, validating that every row has the same
     * number of columns as the header. Defensively copies the given header
     * and rows so subsequent mutation of the arguments does not affect this
     * instance.
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

        this.header = header.clone();
        this.rows = Collections.unmodifiableList(
                rows.stream().map(String[]::clone).collect(Collectors.toList()));
    }

    /**
     * Returns a defensive copy of the column names, so mutating the
     * returned array cannot affect this instance.
     *
     * @return the column names.
     */
    public String[] getHeader() {
        return header.clone();
    }

    /**
     * Returns a defensive, unmodifiable copy of the data rows, so mutating
     * the returned list or any of its rows cannot affect this instance.
     *
     * @return the data rows.
     */
    public List<String[]> getRows() {
        return Collections.unmodifiableList(
                rows.stream().map(String[]::clone).collect(Collectors.toList()));
    }
}