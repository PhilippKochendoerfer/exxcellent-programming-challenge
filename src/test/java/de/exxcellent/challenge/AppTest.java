package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    @Test
    void runFootball() {
        App.main("--football", "src/main/resources/de/exxcellent/challenge/football.csv");
    }
    @Test
    void runWeather() {
        App.main("--weather", "src/main/resources/de/exxcellent/challenge/weather.csv");
    }
    @Test
    void runApp() {
        App.main();
    }

}
