package seedu.address.model.person.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class PersonFlagComparator implements Comparator<Person> {
    public int compare(Person person1, Person person2) {
        boolean person1IsFlagged = person1.getFlag().isFlagged;
        boolean person2IsFlagged = person2.getFlag().isFlagged;
        if (person1IsFlagged == person2IsFlagged) {
            return 0;
        } else if (person1IsFlagged) {
            return -1;
        } else {
            return 1;
        }
    }

}
