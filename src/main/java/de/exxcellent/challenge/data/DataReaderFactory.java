package de.exxcellent.challenge.data;

import java.io.InputStream;

import de.exxcellent.challenge.data.json.JsonReader;

/**
 * Creates the {@link DataReader} matching a given format flag, parsing the
 * given input stream. Where that stream comes from (a file, a URL, ...) is
 * {@link DataSourceFactory}'s concern, not this factory's or the readers'.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class DataReaderFactory {

    /**
     * Creates a {@link DataReader} for the given format, reading from the given input stream.
     *
     * @param format      The format flag: {@code --csv} or {@code --json}.
     * @param inputStream The input stream to read from.
     * @return A {@link DataReader} matching {@code format}.
     * @throws IllegalArgumentException if {@code format} is neither {@code --csv} nor {@code --json}.
     */
    public static DataReader create(String format, InputStream inputStream) {
        switch (format) {
            case "--csv":
                return new CsvReader(inputStream);
            case "--json":
                return new JsonReader(inputStream);
            default:
                throw new IllegalArgumentException("Invalid format parameter. Use --csv or --json.");
        }
    }
}
