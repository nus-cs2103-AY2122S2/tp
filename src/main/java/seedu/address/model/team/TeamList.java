package seedu.address.model.team;

import java.util.ArrayList;

import seedu.address.model.person.Person;

public class TeamList {
    private ArrayList<Person> list;

    public TeamList() {
        this.list = new ArrayList<Person>();
    }

    public TeamList(Person[] teamMembers) {
        this.list = new ArrayList<Person>();
        for (int i = 0; i < teamMembers.length; i++) {
            this.list.add(teamMembers[i]);
        }
    }

    public void addToTeamList(Person person) {
        this.list.add(person);
    }

}
