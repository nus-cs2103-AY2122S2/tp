package seedu.address.model.team;

import java.util.ArrayList;
import seedu.address.model.person.Person;

public class TeamList {
    ArrayList<Person> list;

    TeamList() {
        this.list = new ArrayList<Person>();
    }

    TeamList(Person[] teamMembers) {
        this.list = new ArrayList<Person>();
        for (int i = 0; i < teamMembers.length; i++) {
            this.list.add(teamMembers[i]);
        }
    }

    void addToTeamList(Person person) {
        this.list.add(person);
    }

}
