package seedu.address.model.team;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents a team list in MyGM
 */
public class TeamMemberList {
    private ArrayList<Person> list;

    /**
     * Constructs an empty {@code TeamList}
     */
    public TeamMemberList() {
        this.list = new ArrayList<Person>();
    }

    /**
     * Constructs a {@code TeamList}
     *
     * @param teamMembers An array of players to be added into the TeamList
     */
    public TeamMemberList(Person[] teamMembers) {
        this.list = new ArrayList<Person>();
        for (int i = 0; i < teamMembers.length; i++) {
            this.list.add(teamMembers[i]);
        }
    }

    /**
     * Adds a player into the teamList
     * @param person The player to be added
     */
    public void putToTeamList(Person person) {
        this.list.add(person);
    }

    /**
     * Checks the existence of person
     *
     * @param person The person to be checked
     * @return Boolean represents the existence of person
     */
    public boolean containsPerson(Person person) {
        return this.list.contains(person);
    }

}
