package de.exxcellent.challenge.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.json.JsonReader;
import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Test class for the JsonReader class.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class JsonReaderTest {

    private static final String TINY_VALID_JSON = "src/test/resources/de/exxcellent/challenge/tiny_valid.json";
    private static final String EMPTY_JSON = "src/test/resources/de/exxcellent/challenge/empty.json";
    private static final String MALFORMED_JSON = "src/test/resources/de/exxcellent/challenge/malformed.json";

    @Test
    void readData_validJson_parsesHeaderAndRows() throws IOException, InvalidDataException {
        try (JsonReader jsonReader = new JsonReader(new FileInputStream(TINY_VALID_JSON))) {
            Data data = jsonReader.readData();

            assertArrayEquals(new String[] { "A", "B" }, data.getHeader());
            assertEquals(2, data.getRows().size());
            assertEquals("1", data.getRows().get(0).getValue("A"));
            assertEquals("2", data.getRows().get(0).getValue("B"));
            assertEquals("3", data.getRows().get(1).getValue("A"));
            assertEquals("4", data.getRows().get(1).getValue("B"));
        }
    }

    @Test
    void readData_emptyJson_throwsInvalidDataException() throws IOException {
        try (JsonReader jsonReader = new JsonReader(new FileInputStream(EMPTY_JSON))) {
            assertThrows(InvalidDataException.class, jsonReader::readData);
        }
    }

    @Test
    void readData_malformedJson_throwsInvalidDataException() throws IOException {
        try (JsonReader jsonReader = new JsonReader(new FileInputStream(MALFORMED_JSON))) {
            assertThrows(InvalidDataException.class, jsonReader::readData);
        }
    }
}
