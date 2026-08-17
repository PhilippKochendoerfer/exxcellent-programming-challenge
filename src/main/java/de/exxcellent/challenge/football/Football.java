package de.exxcellent.challenge.football;

import java.io.IOException;

import de.exxcellent.challenge.MinDiff;
import de.exxcellent.challenge.exception.NoDataFoundException;

public class Football {

    public static String getTeamWithSmallestDiff(String filePath) throws IOException, NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(filePath, 0, 5, 6, true);
    }
}
