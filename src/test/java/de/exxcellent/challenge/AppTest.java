package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.exception.NoDataFoundException;
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
    void testGetDayWithSmallestTemperatureSpread() throws IOException, NoDataFoundException{
        assertEquals("14", Weather.getDayWithSmallestTemperatureSpread(WEATHER_CSV), "My expectations were not met");
    }

    @Test
    void testGetTeamWithSmallestGoalDifference() throws IOException, NoDataFoundException{
        assertEquals("Aston_Villa", Football.getTeamWithSmallestDiff(FOOTBALL_CSV), "My expectations were not met");
    }
    @Test
    void runApp() {
        App.main();
    }

}
