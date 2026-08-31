package de.exxcellent.challenge.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.exception.InvalidDataException;


/**
 * Test class for the Data class.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class DataTest {

    @Test
    void constructorThrowsInvalidDataException_whenRowLengthMismatchesHeader() {
        String[] header = { "A", "B" };
        List<String[]> rows = List.<String[]>of(new String[] { "1" });

        assertThrows(InvalidDataException.class, () -> new Data(header, rows),
                "Expected an InvalidDataException to be thrown due to malformed data");
    }

    @Test
    void constructorThrowsIllegalArgumentException_whenHeaderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Data(null, Collections.emptyList()));
    }

    @Test
    void constructorThrowsIllegalArgumentException_whenRowsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Data(new String[] { "A" }, null));
    }

    @Test
    void constructorAcceptsEmptyRowsList() throws InvalidDataException {
        Data data = new Data(new String[] { "A", "B" }, Collections.emptyList());

        assertTrue(data.getRows().isEmpty());
    }

    @Test
    void getHeaderAndGetRows_returnDataEqualToWhatWasPassedIn() throws InvalidDataException {
        String[] header = { "A", "B" };
        List<String[]> rows = List.of(new String[] { "1", "2" }, new String[] { "3", "4" });

        Data data = new Data(header, rows);

        assertArrayEquals(header, data.getHeader());
        assertEquals(rows.size(), data.getRows().size());
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < header.length; j++) {
                assertEquals(rows.get(i)[j], data.getRows().get(i).getValue(header[j]));
            }
        }
    }

    @Test
    void mutatingConstructorArguments_afterConstruction_doesNotAffectData() throws InvalidDataException {
        String[] header = { "A", "B" };
        String[] row = { "1", "2" };
        List<String[]> rows = new ArrayList<>(List.<String[]>of(row));

        Data data = new Data(header, rows);

        header[0] = "changed";
        row[0] = "changed";
        rows.add(new String[] { "3", "4" });

        assertEquals("A", data.getHeader()[0]);
        assertEquals(1, data.getRows().size());
        assertEquals("1", data.getRows().get(0).getValue("A"));
    }

    @Test
    void mutatingReturnedHeader_doesNotAffectData() throws InvalidDataException {
        Data data = new Data(new String[] { "A", "B" }, List.<String[]>of(new String[] { "1", "2" }));

        data.getHeader()[0] = "changed";

        assertEquals("A", data.getHeader()[0]);
        assertEquals("1", data.getRows().get(0).getValue("A"));
    }

    @Test
    void getRows_returnsUnmodifiableList() throws InvalidDataException {
        Data data = new Data(new String[] { "A" }, List.<String[]>of(new String[] { "1" }));
        List<Data.Row> rows = data.getRows();

        assertThrows(UnsupportedOperationException.class, () -> rows.add(null));
    }

    @Test
    void constructorThrowsInvalidDataException_whenHeaderHasDuplicateColumnName() {
        String[] header = { "A", "B", "A" };
        List<String[]> rows = List.<String[]>of(new String[] { "1", "2", "3" });

        assertThrows(InvalidDataException.class, () -> new Data(header, rows));
    }

    @Test
    void rowGetValue_unknownColumnName_throwsInvalidDataException() throws InvalidDataException {
        Data data = new Data(new String[] { "A" }, List.<String[]>of(new String[] { "1" }));
        Data.Row row = data.getRows().get(0);

        assertThrows(InvalidDataException.class, () -> row.getValue("unknown"));
    }

}
