package de.exxcellent.challenge.util;

import java.util.List;

public class Data {

    private final String[] header;
    private final List<String[]> rows;

    public Data(String[] header, List<String[]> rows) {
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
