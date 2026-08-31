package de.exxcellent.challenge.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Opens an {@link InputStream} for a given source flag, independent of the
 * data format it will later be parsed as.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class DataSourceFactory {

    /**
     * Opens an {@link InputStream} for the given source.
     *
     * @param sourceType The source flag: {@code --file} or {@code --url}.
     * @param location   A file path (for {@code --file}) or a URL (for {@code --url}).
     * @return An opened {@link InputStream} for {@code location}.
     * @throws IOException               if the file/URL cannot be opened.
     * @throws IllegalArgumentException  if {@code sourceType} is neither {@code --file} nor {@code --url}.
     */
    public static InputStream open(String sourceType, String location) throws IOException {
        switch (sourceType) {
            case "--file":
                return new FileInputStream(location);
            case "--url":
                return new URL(location).openStream();
            default:
                throw new IllegalArgumentException("Invalid source parameter. Use --file or --url.");
        }
    }
}
