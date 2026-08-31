package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the App CLI entry point.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class AppTest {

    private static final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private static final String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void captureStdOut() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void restoreStdOut() {
        System.setOut(originalOut);
    }

    @Test
    void main_zeroArgs_printsWeatherAndFootballResults() {
        App.main();

        String expected = "Day with smallest temperature spread : 14" + System.lineSeparator()
                + "Team with smallest goal spread : Aston_Villa" + System.lineSeparator();
        assertEquals(expected, capturedOut.toString());
    }

    @Test
    void main_weatherArgs_printsWeatherResult() {
        App.main("--weather", WEATHER_CSV);

        assertEquals("Day with smallest temperature spread : 14" + System.lineSeparator(), capturedOut.toString());
    }

    @Test
    void main_footballArgs_printsFootballResult() {
        App.main("--football", FOOTBALL_CSV);

        assertEquals("Team with smallest goal spread : Aston_Villa" + System.lineSeparator(), capturedOut.toString());
    }

}
