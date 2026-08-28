package de.exxcellent.challenge.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class to read data from a file and create table-like data structure.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public abstract class DataFileReader extends DataReader {

    /**
     * Opens the file at the given path for reading.
     *
     * @param filePath path to the file to read.
     * @throws FileNotFoundException if no file exists at the given path.
     */
    public DataFileReader(String filePath) throws FileNotFoundException {
        super(new FileInputStream(filePath));
    }

    @Override
    public abstract Data readData() throws IOException;

}
