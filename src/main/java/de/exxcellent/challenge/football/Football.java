package de.exxcellent.challenge.football;

import de.exxcellent.challenge.MinDiff;

public class Football {

    public static String getTeamWithSmallestDiff(String filePath) {
        String team = MinDiff.getKeyWithSmallestDiff(filePath, 0, 5, 6, true);
        if (team == null) {
            throw new RuntimeException("No team found with valid goal difference data");
        }
        return team;
    }
}
