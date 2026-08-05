package de.exxcellent.challenge.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to read data from a CSV file and create a Data object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@uni-ulm.de>
 */
public class CsvReader extends DataFileReader {

    public CsvReader(String filePath) {
        super(filePath);
    }

    @Override
    public Data readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String[] header = reader.readLine().split(",");
            List<String[]> rows = reader.lines().map(line -> line.split(","))
                    .collect(Collectors.toList());
            return new Data(header, rows);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + e.getMessage());
        }
    }
}
