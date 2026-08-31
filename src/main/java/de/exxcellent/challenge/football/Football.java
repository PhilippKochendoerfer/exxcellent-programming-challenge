package de.exxcellent.challenge.football;

import de.exxcellent.challenge.calc.MinDiff;
import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.exception.InvalidDataException;

/**
 * Provides football-specific analysis over {@link Data}.
 */
public class Football {

    /**
     * Returns the team with the smallest difference between goals scored and goals
     * conceded.
     *
     * @param data The data to analyze.
     * @return The team with the smallest difference.
     * @throws InvalidDataException if the data contains no row usable for the computation
    */

    public static String getTeamWithSmallestDiff(Data data) throws InvalidDataException {
        return MinDiff.getKeyWithSmallestDiff(data, "Team", "Goals", "Goals Allowed", true);
    }
}
