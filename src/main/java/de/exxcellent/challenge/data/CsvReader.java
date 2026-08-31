package de.exxcellent.challenge.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Class to read data from a CSV input stream and create a Data object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class CsvReader extends DataReader {

    /**
     * Creates a new CsvReader reading from the given input stream.
     *
     * @param inputStream The input stream to read CSV data from.
     */
    public CsvReader(InputStream inputStream) {
        super(inputStream);
    }

    /**
     * Reads data from the CSV input stream and creates a Data object.
     * @return The Data object containing the header and rows from the CSV data.
     * @throws IOException          if the input stream cannot be read.
     * @throws InvalidDataException if the CSV rows are not well-formed.
     */
    @Override
    public Data readData() throws IOException, InvalidDataException {
        String[] header = bufferedReader.readLine().split(",");
        List<String[]> rows = bufferedReader.lines().map(line -> line.split(","))
                .collect(Collectors.toList());
        return new Data(header, rows);

    }
}
