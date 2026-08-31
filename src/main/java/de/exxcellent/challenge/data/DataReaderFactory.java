package de.exxcellent.challenge.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.exxcellent.challenge.data.json.JsonReader;

/**
 * Creates the {@link DataReader} matching a given format flag, reading from
 * the file at a given path. The concrete readers themselves only know how to
 * parse an {@link InputStream}; opening the file is this factory's job, so a
 * future source other than a local file (e.g. a URL) only needs a new
 * factory method, not changes to {@link CsvReader}/{@link JsonReader}.
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
                return new CsvReader(new FileInputStream(filePath));
            case "--json":
                return new JsonReader(new FileInputStream(filePath));
            default:
                throw new IllegalArgumentException("Invalid format parameter. Use --csv or --json.");
        }
    }
}
