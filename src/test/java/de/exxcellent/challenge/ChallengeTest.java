package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.exception.InvalidDataException;

class ChallengeTest {

    private static final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private static final String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    @Test
    void challenge_weather_returnsFormattedMessage() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--weather", WEATHER_CSV);

        assertEquals("Day with smallest temperature spread : 14", result);
    }

    @Test
    void challenge_football_returnsFormattedMessage() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--football", FOOTBALL_CSV);

        assertEquals("Team with smallest goal spread : Aston_Villa", result);
    }

    @Test
    void challenge_unknownApp_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Challenge.challenge("--foo", WEATHER_CSV));
    }

    @Test
    void challenge_missingFile_throwsIOException() {
        assertThrows(IOException.class, () -> Challenge.challenge("--weather", "does/not/exist.csv"));
    }
}
