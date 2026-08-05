package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;


/**
 * Example JUnit 5 test case.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@uni-ulm.de>
 */
class DataTest {


    private final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private final String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    private final String WEATHER_CSV_NOT_WELL_FORMED = "src/test/resources/de/excellent/challenge/weather_not_well_formed.csv";


    @Test
    void testGetDayWithSmallestTemperatureSpread() {
        assertEquals("14", Weather.getDayWithSmallestTemperatureSpread(WEATHER_CSV), "My expectations were not met");
    }

    @Test
    void testGetTeamWithSmallestGoalDifference() {
        assertEquals("Aston_Villa", Football.getTeamWithSmallestDiff(FOOTBALL_CSV), "My expectations were not met");
    }

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
