package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class EmailComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getEmail().compareTo(p2.getEmail());
    }
}
