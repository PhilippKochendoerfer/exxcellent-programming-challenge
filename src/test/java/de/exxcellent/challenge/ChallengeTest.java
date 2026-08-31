package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.exception.InvalidDataException;

class ChallengeTest {

    private static final String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private static final String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";
    private static final String WEATHER_JSON = "src/main/resources/de/exxcellent/challenge/weather.json";
    private static final String FOOTBALL_JSON = "src/main/resources/de/exxcellent/challenge/football.json";

    @Test
    void challenge_weatherCsv_returnsFormattedMessage() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--weather", "--csv", "--file", WEATHER_CSV);

        assertEquals("Day with smallest temperature spread : 14", result);
    }

    @Test
    void challenge_footballCsv_returnsFormattedMessage() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--football", "--csv", "--file", FOOTBALL_CSV);

        assertEquals("Team with smallest goal spread : Aston_Villa", result);
    }

    @Test
    void challenge_weatherJson_returnsSameResultAsCsv() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--weather", "--json", "--file", WEATHER_JSON);

        assertEquals("Day with smallest temperature spread : 14", result);
    }

    @Test
    void challenge_footballJson_returnsSameResultAsCsv() throws IOException, InvalidDataException {
        String result = Challenge.challenge("--football", "--json", "--file", FOOTBALL_JSON);

        assertEquals("Team with smallest goal spread : Aston_Villa", result);
    }

    @Test
    void challenge_unknownApp_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Challenge.challenge("--foo", "--csv", "--file", WEATHER_CSV));
    }

    @Test
    void challenge_unknownFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Challenge.challenge("--weather", "--xml", "--file", WEATHER_CSV));
    }

    @Test
    void challenge_unknownSourceType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Challenge.challenge("--weather", "--csv", "--ftp", WEATHER_CSV));
    }

    @Test
    void challenge_missingFile_throwsIOException() {
        assertThrows(IOException.class,
                () -> Challenge.challenge("--weather", "--csv", "--file", "does/not/exist.csv"));
    }
}
