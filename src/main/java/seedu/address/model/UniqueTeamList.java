package seedu.address.model;


import java.util.HashMap;

import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Represents a list of unique Teams
 */
public class UniqueTeamList {
    private final HashMap<TeamName, Team> teamNameToTeamMap;

    /**
     * Constructs a {@code UniqueTeamList}
     */
    public UniqueTeamList() {
        this.teamNameToTeamMap = new HashMap<TeamName, Team>();
    }

    /**
     * Checks the UniqueTeamList contains a team using TeamName
     *
     * @param teamName The TeamName to check
     * @return Boolean of it contains the TeamName
     */
    public boolean containsTeam(TeamName teamName) {
        return this.teamNameToTeamMap.containsKey(teamName);
    }

    /**
     * Checks the UniqueTeamList contains a team using Team
     *
     * @param team The Team to check
     * @return Boolean of it contains the Team
     */
    public boolean containsTeam(Team team) {
        return containsTeam(team.getTeamName());
    }

    /**
     * Adds a Team to UniqueTeamList
     *
     * @param team The Team to add
     */
    public void addTeam(Team team) {
        TeamName teamName = team.getTeamName();
        if (!this.teamNameToTeamMap.containsKey(teamName)) {
            this.teamNameToTeamMap.put(teamName, team);
        }
    }

    /**
     * Deletes a Team from UniqueTeamList
     *
     * @param team The Team to delete
     */
    public void deleteTeam(Team team) {
        TeamName teamName = team.getTeamName();
        if (this.teamNameToTeamMap.containsKey(teamName)) {
            this.teamNameToTeamMap.remove(teamName);
        }
    }
}

