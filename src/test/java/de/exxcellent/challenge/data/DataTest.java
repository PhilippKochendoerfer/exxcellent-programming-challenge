package de.exxcellent.challenge.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


/**
 * Test class for the Data class.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class DataTest {

    private final String WEATHER_CSV_NOT_WELL_FORMED = "src/test/resources/de/exxcellent/challenge/weather_not_well_formed.csv";

    @Test
    void testWellFormedData() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] header;
            List<String[]> rows;
            try (BufferedReader reader = new BufferedReader(new FileReader(WEATHER_CSV_NOT_WELL_FORMED))) {
                header = reader.readLine().split(",");
                rows = reader.lines().map(line -> line.split(","))
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                System.getLogger(DataTest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                throw ex;
            }
            new Data(header, rows);
        }, "Expected an IllegalArgumentException to be thrown due to malformed data");
    }

}
