package seedu.address.model.team;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents a team list in MyGM
 */
public class TeamList {
    private ArrayList<Person> list;

    /**
     * Constructs an empty {@code TeamList}
     */
    public TeamList() {
        this.list = new ArrayList<Person>();
    }

    /**
     * Constructs a {@code TeamList}
     *
     * @param teamMembers An array of players to be added into the TeamList
     */
    public TeamList(Person[] teamMembers) {
        this.list = new ArrayList<Person>();
        for (int i = 0; i < teamMembers.length; i++) {
            this.list.add(teamMembers[i]);
        }
    }

    /**
     * Add a player into the teamList
     * @param person The player to be added
     */
    public void addToTeamList(Person person) {
        this.list.add(person);
    }

}
