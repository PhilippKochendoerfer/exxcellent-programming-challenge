
package de.exxcellent.challenge.data;

import java.util.List;
/**
 * Class to represent table-like data structure with header and rows.
 */
public class Data {
    private final String[] header;
    private final List<String[]> rows;

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