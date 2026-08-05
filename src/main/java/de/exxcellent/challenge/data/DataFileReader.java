package de.exxcellent.challenge.data;

/**
 * Class to read data from a file and create table-like data structure.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@uni-ulm.de>
 */
public abstract class DataFileReader {

    protected String filePath;

    public DataFileReader(String filePath) {
        this.filePath = filePath;
    }

    public abstract Data readData();
}
