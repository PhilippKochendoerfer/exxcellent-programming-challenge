package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    @Test
    void testWheatherMinimumSpread() {
        String dayWithSmallestTempSpread = Weather.getDayWithSmallestTemperatureSpread();
        assertEquals("14", dayWithSmallestTempSpread);
    }

//     @Test
//     void runFootball() {
//         App.main("--football", "football.csv");
//     }

}