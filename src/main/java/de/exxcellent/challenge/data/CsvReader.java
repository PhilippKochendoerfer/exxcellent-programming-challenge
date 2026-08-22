package de.exxcellent.challenge.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to read data from a CSV file and create a Data object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@uni-ulm.de>
 */
public class CsvReader extends DataFileReader {

    public CsvReader(String filePath) throws FileNotFoundException {
        super(filePath);
    }

    @Override
    public Data readData() throws IOException {
        String[] header = bufferedReader.readLine().split(",");
        List<String[]> rows = bufferedReader.lines().map(line -> line.split(","))
                .collect(Collectors.toList());
        return new Data(header, rows);

    }
}
