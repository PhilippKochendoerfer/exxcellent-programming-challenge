package de.exxcellent.challenge.football;

import java.io.IOException;

import de.exxcellent.challenge.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;

public class Football {

    public static String getTeamWithSmallestDiff(Data data) throws IOException, NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, 0, 5, 6, true);
    }
}
