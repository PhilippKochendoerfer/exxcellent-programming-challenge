package de.exxcellent.challenge.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Test class for the CsvReader class.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class CsvReaderTest {

    private static final String TINY_VALID_CSV = "src/test/resources/de/exxcellent/challenge/tiny_valid.csv";
    private static final String HEADER_ONLY_CSV = "src/test/resources/de/exxcellent/challenge/header_only.csv";
    private static final String MALFORMED_CSV = "src/test/resources/de/exxcellent/challenge/malformed.csv";

    @Test
    void readData_validCsv_parsesHeaderAndRows() throws IOException, InvalidDataException {
        try (CsvReader csvReader = new CsvReader(TINY_VALID_CSV)) {
            Data data = csvReader.readData();

            assertArrayEquals(new String[] { "A", "B" }, data.getHeader());
            assertEquals(2, data.getRows().size());
            assertArrayEquals(new String[] { "1", "2" }, data.getRows().get(0));
            assertArrayEquals(new String[] { "3", "4" }, data.getRows().get(1));
        }
    }

    @Test
    void readData_headerOnlyCsv_producesEmptyRows() throws IOException, InvalidDataException {
        try (CsvReader csvReader = new CsvReader(HEADER_ONLY_CSV)) {
            Data data = csvReader.readData();

            assertArrayEquals(new String[] { "A", "B" }, data.getHeader());
            assertTrue(data.getRows().isEmpty());
        }
    }

    @Test
    void constructor_missingFile_throwsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> new CsvReader("does/not/exist.csv"));
    }

    @Test
    void readData_malformedCsv_throwsInvalidDataException() throws IOException {
        try (CsvReader csvReader = new CsvReader(MALFORMED_CSV)) {
            assertThrows(InvalidDataException.class, csvReader::readData);
        }
    }
}
