package de.exxcellent.challenge.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
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
        try (CsvReader csvReader = new CsvReader(new FileInputStream(TINY_VALID_CSV))) {
            Data data = csvReader.readData();

            assertArrayEquals(new String[] { "A", "B" }, data.getHeader());
            assertEquals(2, data.getRows().size());
            assertEquals("1", data.getRows().get(0).getValue("A"));
            assertEquals("2", data.getRows().get(0).getValue("B"));
            assertEquals("3", data.getRows().get(1).getValue("A"));
            assertEquals("4", data.getRows().get(1).getValue("B"));
        }
    }

    @Test
    void readData_headerOnlyCsv_producesEmptyRows() throws IOException, InvalidDataException {
        try (CsvReader csvReader = new CsvReader(new FileInputStream(HEADER_ONLY_CSV))) {
            Data data = csvReader.readData();

            assertArrayEquals(new String[] { "A", "B" }, data.getHeader());
            assertTrue(data.getRows().isEmpty());
        }
    }

    @Test
    void readData_malformedCsv_throwsInvalidDataException() throws IOException {
        try (CsvReader csvReader = new CsvReader(new FileInputStream(MALFORMED_CSV))) {
            assertThrows(InvalidDataException.class, csvReader::readData);
        }
    }
}
