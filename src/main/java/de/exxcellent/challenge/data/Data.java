
package de.exxcellent.challenge.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Immutable class to represent table-like data structure with header and rows.
 * 
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public final class Data implements Iterable<Data.Row> {
    private final String[] header;
    private final List<Row> rows;
    private final Map<String, Integer> headerIndexMap;

    /**
     * Creates a new Data instance, validating that every row has the same
     * number of columns as the header. Defensively copies the given header
     * and rows so subsequent mutation of the arguments does not affect this
     * instance.
     *
     * @param header The column names.
     * @param rows   The data rows; each row's length must match {@code header}'s
     *               length.
     * @throws IllegalArgumentException if header or rows is null.
     * @throws InvalidDataException     if any row's length differs from the
     *                                  header's length, or if the header
     *                                  contains a duplicate column name.
     */
    public Data(String[] header, List<String[]> rows) throws InvalidDataException {
        if (header == null || rows == null) {
            throw new IllegalArgumentException("Header and rows cannot be null");
        }

        this.header = header.clone();

        this.headerIndexMap = new HashMap<>();
        for (int i = 0; i < this.header.length; i++) {
            if (headerIndexMap.containsKey(this.header[i])) {
                throw new InvalidDataException("Duplicate column name: " + this.header[i]);
            }
            headerIndexMap.put(this.header[i], i);
        }

        List<Row> builtRows = new ArrayList<>(rows.size());
        for (String[] row : rows) {
            if (row.length != this.header.length) {
                throw new InvalidDataException("All rows must have the same number of columns as the header");
            }
            builtRows.add(new Row(row.clone()));
        }
        this.rows = Collections.unmodifiableList(builtRows);
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
     * Returns this table's rows. The returned list is unmodifiable, and its
     * {@link Row} elements expose no way to mutate this instance's data.
     *
     * @return the data rows.
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Returns an iterator over this table's rows, in the same order as
     * {@link #getRows()}.
     *
     * @return an iterator over the rows.
     */
    @Override
    public Iterator<Row> iterator() {
        return getRows().iterator();
    }

    /**
     * A single row of this {@link Data} table, giving access to its values by
     * column name instead of by position.
     */
    public final class Row {
        private final String[] values;

        private Row(String[] values) {
            this.values = values;
        }

        /**
         * Returns the value in this row for the given column name.
         *
         * @param columnName The column name to look up.
         * @return the value in this row for {@code columnName}.
         * @throws InvalidDataException if {@code columnName} is not present in the header.
         */
        public String getValue(String columnName) throws InvalidDataException {
            Integer index = headerIndexMap.get(columnName);
            if (index == null) {
                throw new InvalidDataException("Column name not found: " + columnName);
            }
            return values[index];
        }

    }
}
