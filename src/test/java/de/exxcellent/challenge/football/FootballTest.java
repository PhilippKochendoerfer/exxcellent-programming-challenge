package de.exxcellent.challenge.football;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataReader;
import de.exxcellent.challenge.exception.InvalidDataException;

class FootballTest {

    private static final String[] HEADER = {
            "Team", "Games", "Wins", "Losses", "Draws", "Goals", "Goals Allowed", "Points" };

    private static Data data(String[]... rows) throws InvalidDataException {
        return new Data(HEADER, Arrays.asList(rows));
    }

    private static String[] row(String team, String goals, String goalsAllowed) {
        return new String[] { team, "0", "0", "0", "0", goals, goalsAllowed, "0" };
    }

    @Test
    void getTeamWithSmallestDiff_smallFixture_returnsTeamWithSmallestDiff() throws InvalidDataException {
        Data data = data(
                row("TeamA", "50", "40"),
                row("TeamB", "30", "28"),
                row("TeamC", "45", "45"));

        String result = Football.getTeamWithSmallestDiff(data);

        assertEquals("TeamC", result);
    }

    @Test
    void getTeamWithSmallestDiff_productionCsv_returnsAstonVilla() throws IOException, InvalidDataException {
        try (DataReader dataReader = new CsvReader(
                new FileInputStream("src/main/resources/de/exxcellent/challenge/football.csv"))) {
            assertEquals("Aston_Villa", Football.getTeamWithSmallestDiff(dataReader.readData()));
        }
    }
}
