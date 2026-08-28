package de.exxcellent.challenge.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Abstract class to read data from an input stream and create table-like data
 * structure.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public abstract class DataReader implements AutoCloseable {

    protected final BufferedReader bufferedReader;

    /**
     * Creates a new DataReader with the given input stream.
     * 
     * @param inputStream The input stream to read from.
     */
    public DataReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Reads data from the input stream and creates a table-like data structure.
     * 
     * @return The data read from the input stream.
     * @throws IOException if the input stream cannot be read.
     */
    public abstract Data readData() throws IOException;

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
