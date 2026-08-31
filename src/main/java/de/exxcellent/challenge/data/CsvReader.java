package de.exxcellent.challenge.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Class to read data from a CSV file and create a Data object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class CsvReader extends DataFileReader {

    /**
     * Opens the CSV file at the given path for reading.
     *
     * @param filePath path to the CSV file to read.
     * @throws FileNotFoundException if no file exists at the given path.
     */
    public CsvReader(String filePath) throws FileNotFoundException {
        super(filePath);
    }

    /**
     * Reads data from the CSV file and creates a Data object.
     * @return The Data object containing the header and rows from the CSV file.
     * @throws IOException          if the file cannot be read.
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
