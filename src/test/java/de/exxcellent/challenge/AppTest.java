package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.DataReader;
import de.exxcellent.challenge.exception.InvalidDataException;
import de.exxcellent.challenge.football.Football;
import de.exxcellent.challenge.weather.Weather;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private final String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    @Test
    void testGetDayWithSmallestTemperatureSpread() throws IOException, InvalidDataException{
        DataReader dataReader = new CsvReader(WEATHER_CSV);
        assertEquals("14", Weather.getDayWithSmallestTemperatureSpread(dataReader.readData()), "My expectations were not met");
        dataReader.close();
    }

    @Test
    void testGetTeamWithSmallestGoalDifference() throws IOException, InvalidDataException{
        DataReader dataReader = new CsvReader(FOOTBALL_CSV);
        assertEquals("Aston_Villa", Football.getTeamWithSmallestDiff(dataReader.readData()), "My expectations were not met");
        dataReader.close();
    }
    @Test
    void runApp() {
        App.main("--weather", WEATHER_CSV);
    }

}
