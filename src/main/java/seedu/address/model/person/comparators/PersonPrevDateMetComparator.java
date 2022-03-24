package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.PrevDateMet;

/**
 * Comparator which compares the PrevDateMet of two persons.
 * Used to sort {@code UniquePersonList} by PrevDateMet.
 */
public class PersonPrevDateMetComparator extends PersonFlagComparator implements Comparator<Person> {

    /**
     * Method to determine the order of two persons after sorting.
     * Flagged persons will be shifted to the front followed by comparing PrevDateMet.
     * Returns 0 if date is equal, -1 if this PrevDateMet is before and 1 if it is after.
     *
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Integer representing if PrevDateMet of person 1 is equal, before or after person 2.
     */
    @Override
    public int compare(Person person1, Person person2) {
        int flagCompare = super.compare(person1, person2);
        if (flagCompare == 0) {
            PrevDateMet person1PrevDateMet = person1.getPrevDateMet();
            PrevDateMet person2PrevDateMet = person2.getPrevDateMet();
            return person1PrevDateMet.compare(person2PrevDateMet);
        } else {
            return flagCompare;
        }
    }

}
