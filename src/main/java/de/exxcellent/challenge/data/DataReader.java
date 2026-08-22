package de.exxcellent.challenge.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class DataReader implements AutoCloseable {

    protected final BufferedReader bufferedReader;

    public DataReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public abstract Data readData() throws IOException;

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
