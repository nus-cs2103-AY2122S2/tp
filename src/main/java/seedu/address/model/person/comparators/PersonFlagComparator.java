package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class PersonFlagComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        boolean person1IsFlagged = person1.getFlag().isFlagged;
        boolean person2IsFlagged = person2.getFlag().isFlagged;
        return getComparison(person1IsFlagged, person2IsFlagged);
    }

    private int getComparison(boolean person1IsFlagged, boolean person2IsFlagged) {
        if (person1IsFlagged == person2IsFlagged) {
            return 0;
        } else if (person1IsFlagged) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof PersonFlagComparator;
    }
}
