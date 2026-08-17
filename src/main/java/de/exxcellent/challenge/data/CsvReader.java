package de.exxcellent.challenge.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    public Data readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String[] header = reader.readLine().split(",");
        List<String[]> rows = reader.lines().map(line -> line.split(","))
                .collect(Collectors.toList());
        reader.close();
        return new Data(header, rows);

    }
}
