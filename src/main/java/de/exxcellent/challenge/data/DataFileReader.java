package de.exxcellent.challenge.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class to read data from a file and create table-like data structure.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@uni-ulm.de>
 */
public abstract class DataFileReader extends DataReader {

    public DataFileReader(String filePath) throws FileNotFoundException {
        super(new FileInputStream(filePath));
    }

    @Override
    public abstract Data readData() throws IOException;

}
