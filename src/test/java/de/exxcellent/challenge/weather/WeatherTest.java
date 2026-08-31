package de.exxcellent.challenge.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.data.CsvReader;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataReader;
import de.exxcellent.challenge.exception.InvalidDataException;

class WeatherTest {

    private static final String[] HEADER = {
            "Day", "MxT", "MnT", "AvT", "AvDP", "1HrP TPcpn", "PDir", "AvSp", "Dir", "MxS", "SkyC", "MxR", "Mn",
            "R AvSLP" };

    private static Data data(String[]... rows) throws InvalidDataException {
        return new Data(HEADER, Arrays.asList(rows));
    }

    private static String[] row(String day, String mxT, String mnT) {
        return new String[] { day, mxT, mnT, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
    }

    @Test
    void getDayWithSmallestTemperatureSpread_smallFixture_returnsDayWithSmallestSpread() throws InvalidDataException {
        Data data = data(
                row("1", "80", "60"),
                row("2", "70", "65"),
                row("3", "75", "74"));

        String result = Weather.getDayWithSmallestTemperatureSpread(data);

        assertEquals("3", result);
    }

    @Test
    void getDayWithSmallestTemperatureSpread_productionCsv_returnsDay14() throws IOException, InvalidDataException {
        try (DataReader dataReader = new CsvReader("src/main/resources/de/exxcellent/challenge/weather.csv")) {
            assertEquals("14", Weather.getDayWithSmallestTemperatureSpread(dataReader.readData()));
        }
    }
}
