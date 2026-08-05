package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.weather.Weather;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";

    @Test
    void testGetDayWithSmallestTemperatureSpread() {
        assertEquals("14", Weather.getDayWithSmallestTemperatureSpread(WEATHER_CSV), "My expectations were not met");
    }

    // @Test
    // void runFootball() {
    //     App.main("--football", "football.csv");
    // }

}
