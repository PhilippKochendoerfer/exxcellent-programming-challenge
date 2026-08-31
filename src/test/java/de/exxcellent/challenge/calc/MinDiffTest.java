package de.exxcellent.challenge.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.InvalidDataException;

class MinDiffTest {

    private static Data data(String[] header, String[]... rows) throws InvalidDataException {
        return new Data(header, Arrays.asList(rows));
    }

    @Test
    void getKeyWithSmallestDiff_singleRow_returnsThatRowsKey() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" });

        String result = MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);

        assertEquals("X", result);
    }

    @Test
    void getKeyWithSmallestDiff_multipleRows_returnsRowWithSmallestDiff() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" },
                new String[] { "Y", "2", "5" },
                new String[] { "Z", "8", "8" });

        String result = MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);

        assertEquals("Y", result);
    }

    @Test
    void getKeyWithSmallestDiff_multipleRows_returnsRowWithSmallestDiff_ignoreNegative() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" },
                new String[] { "Y", "2", "5" },
                new String[] { "Z", "8", "8" });

        String result = MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, true);

        assertEquals("Z", result);
    }

    @Test
    void getKeyWithSmallestDiff_noRows_throwsInvalidDataException() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" });

        assertThrows(InvalidDataException.class, () -> {
            MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);
        });
    }

    @Test
    void getKeyWithSmallestDiff_multipleRowsTies_returnsFirstRowWithSmallestDiff() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" },
                new String[] { "Y", "2", "5" },
                new String[] { "Z", "8", "8" },
                new String[] { "W", "1", "4" });

        String result = MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);

        assertEquals("Y", result);
    }

    @Test
    void getKeyWithSmallestDiff_multipleRowsTies_ignoreNegative_returnsFirstRowWithSmallestDiff() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "W", "10", "3" },
                new String[] { "X", "2", "2" },
                new String[] { "Y", "8", "8" },
                new String[] { "Z", "1", "20" });


        String result = MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, true);

        assertEquals("X", result);
    }

    @Test
    void getKeyWithSmallestDiff_negativeColumnIndex_throwsIllegalArgumentException() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" });

        assertThrows(IllegalArgumentException.class, () -> {
            MinDiff.getKeyWithSmallestDiff(data, 0, 1, -1, false);
        });
    }

    @Test
    void getKeyWithSmallestDiff_tooLargeColumnIndex_throwsInvalidDataException() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" });

        assertThrows(InvalidDataException.class, () -> {
            MinDiff.getKeyWithSmallestDiff(data, 0, 1, 3, false);
        });
    }

    @Test
    void getKeyWithSmallestDiff_emptyHeaderAndRows_throwsInvalidDataException() throws InvalidDataException {
        Data data = data(new String[0]);

        assertThrows(InvalidDataException.class, () -> {
            MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);
        });
    }

    @Test
    void getKeyWithSmallestDiff_nonNumericValue_throwsInvalidDataException() throws InvalidDataException {
        Data data = data(
                new String[] { "Key", "A", "B" },
                new String[] { "X", "10", "3" },
                new String[] { "Y", "not-a-number", "5" });

        assertThrows(InvalidDataException.class, () -> {
            MinDiff.getKeyWithSmallestDiff(data, 0, 1, 2, false);
        });
    }
}