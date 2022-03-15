package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.PrevDateMet;

/**
 * Comparator which compares the PrevDateMet of two persons.
 * Used to sort {@code UniquePersonList} by PrevDateMet.
 */
public class PersonPrevDateMetComparator implements Comparator<Person> {

    /**
     * Method to determine the order of two persons after sorting.
     * Flagged persons will be shifted to the front followed by comparing PrevDateMet.
     * Returns 0 if date is equal, -1 if this PrevDateMet is before and 1 if it is after.
     *
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Integer representing if PrevDateMet of person 1 is equal, before or after person2.
     */
    public int compare(Person person1, Person person2) {
        Boolean person1IsFlagged = person1.getFlag().isFlagged;
        Boolean person2IsFlagged = person2.getFlag().isFlagged;
        if (person1IsFlagged == person2IsFlagged) {
            PrevDateMet person1PrevDateMet = person1.getPrevDateMet();
            PrevDateMet person2PrevDateMet = person2.getPrevDateMet();
            return person1PrevDateMet.compare(person2PrevDateMet);
        } else if (person1IsFlagged) {
            return -1;
        } else {
            return 1;
        }

    }

}
