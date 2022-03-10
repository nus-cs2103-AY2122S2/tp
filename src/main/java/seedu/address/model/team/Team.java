package seedu.address.model.team;

import seedu.address.model.person.Person;

public class Team {
    private final TeamName teamName;
    private final TeamList teamList;

    Team(TeamName teamName, Person[] teamMembers) {
        this.teamName = teamName;
        this.teamList = new TeamList(teamMembers);
    }

    Team(TeamName teamName) {
        this.teamName = teamName;
        this.teamList = new TeamList();
    }

    void addToTeam(Person person) {
        this.teamList.addToTeamList(person);
    }
}
