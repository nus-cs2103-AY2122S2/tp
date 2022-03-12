package seedu.address.model.team;

import seedu.address.model.person.Person;

/**
 * Represents a basketball Team in MyGM
 */

public class Team {
    private final TeamName teamName;
    private final TeamList teamList;

    /**
     * Constructs a {@code Team}
     *
     * @param teamName A valid team name
     * @param teamList A teamList contains the team members
     */
    public Team(TeamName teamName, TeamList teamList) {
        this.teamName = teamName;
        this.teamList = teamList;
    }

    /**
     * Adds a player into this team
     * @param person The player to be added
     */
    public void addToTeam(Person person) {
        this.teamList.addToTeamList(person);
    }
}
