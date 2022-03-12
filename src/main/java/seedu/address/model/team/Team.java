package seedu.address.model.team;

import seedu.address.model.person.Person;



public class Team {
    private final TeamName teamName;
    private final TeamList teamList;

    public Team(TeamName teamName, Person[] teamMembers) {
        this.teamName = teamName;
        this.teamList = new TeamList(teamMembers);
    }

    public Team(TeamName teamName) {
        this.teamName = teamName;
        this.teamList = new TeamList();
    }

    public void addToTeam(Person person) {
        this.teamList.addToTeamList(person);
    }
}
