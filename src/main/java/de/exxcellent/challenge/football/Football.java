package de.exxcellent.challenge.football;

import de.exxcellent.challenge.calc.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.NoDataFoundException;

public class Football {

    /**
     * Returns the team with the smallest difference between goals scored and goals
     * conceded.
     *
     * @param data The data to analyze.
     * @return The team with the smallest difference.
     * @throws IOException
     * @throws NoDataFoundException
    */

    public static String getTeamWithSmallestDiff(Data data) throws NoDataFoundException {
        return MinDiff.getKeyWithSmallestDiff(data, 0, 5, 6, true);
    }
}
