package de.exxcellent.challenge.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.json.JsonReader;
import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Test class for the DataReaderFactory class.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class DataReaderFactoryTest {

    private static final String TINY_VALID_CSV = "src/test/resources/de/exxcellent/challenge/tiny_valid.csv";
    private static final String TINY_VALID_JSON = "src/test/resources/de/exxcellent/challenge/tiny_valid.json";

    @Test
    void create_csv_returnsCsvReader() throws IOException {
        try (DataReader dataReader = DataReaderFactory.create("--csv", new FileInputStream(TINY_VALID_CSV))) {
            assertInstanceOf(CsvReader.class, dataReader);
        }
    }

    @Test
    void create_json_returnsJsonReader() throws IOException {
        try (DataReader dataReader = DataReaderFactory.create("--json", new FileInputStream(TINY_VALID_JSON))) {
            assertInstanceOf(JsonReader.class, dataReader);
        }
    }

    @Test
    void create_csv_readsSameDataAsCsvReader() throws IOException, InvalidDataException {
        try (DataReader dataReader = DataReaderFactory.create("--csv", new FileInputStream(TINY_VALID_CSV))) {
            Data data = dataReader.readData();

            assertEquals(2, data.getRows().size());
            assertEquals("1", data.getRows().get(0).getValue("A"));
        }
    }

    @Test
    void create_unknownFormat_throwsIllegalArgumentException() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(TINY_VALID_CSV)) {
            assertThrows(IllegalArgumentException.class, () -> DataReaderFactory.create("--xml", inputStream));
        }
    }
}
