package de.exxcellent.challenge.data;

import java.io.FileNotFoundException;

import de.exxcellent.challenge.data.json.JsonReader;

/**
 * Creates the {@link DataReader} matching a given format flag.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class DataReaderFactory {

    /**
     * Creates a {@link DataReader} for the given format, opened on the given file.
     *
     * @param format   The format flag: {@code --csv} or {@code --json}.
     * @param filePath Path to the file to read.
     * @return A {@link DataReader} matching {@code format}, already opened on {@code filePath}.
     * @throws FileNotFoundException    if no file exists at the given path.
     * @throws IllegalArgumentException if {@code format} is neither {@code --csv} nor {@code --json}.
     */
    public static DataReader create(String format, String filePath) throws FileNotFoundException {
        switch (format) {
            case "--csv":
                return new CsvReader(filePath);
            case "--json":
                return new JsonReader(filePath);
            default:
                throw new IllegalArgumentException("Invalid format parameter. Use --csv or --json.");
        }
    }
}
