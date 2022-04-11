package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
