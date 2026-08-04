
package de.exxcellent.challenge.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple CSV reader wrapper oround BufferedReader to read CSV files line by line and split them into String arrays.
 * @author Philipp Kochendörfer
 */
public class CsvReader {

    BufferedReader reader;
    int lineNumber = 0;

    public CsvReader(String filePath, String delimiter) {

        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            System.getLogger(CsvReader.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public String[] readHeader() throws IOException {
        if (lineNumber > 0) {
            throw new IllegalStateException("Header can only be read once and must be the first line.");
        }
        String headerLine = reader.readLine();
        if (headerLine != null) {
            lineNumber++;
            return headerLine.trim().split(",");
        } else {
            return null;
        }
    }

    public String[] readLine() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            lineNumber++;
            return line.trim().split(",");
        } else {
            return null;
        }
    }
}
