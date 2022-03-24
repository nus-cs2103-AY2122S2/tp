package seedu.address.model.person.comparators;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.Comparator;

public class PersonNameComparator extends PersonFlagComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        int flagCompare = super.compare(person1, person2);
        if (flagCompare == 0) {
            Name person1Name = person1.getName();
            Name person2Name = person2.getName();
            return person1Name.compare(person2Name);
        } else {
            return flagCompare;
        }
    }
}
