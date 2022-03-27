package seedu.address.model.person.comparators;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * Compares two {@code Person} based on the priority level of their tags.
 */
public class TagPriorityComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        ArrayList<Tag> tags1 = p1.getTags();
        ArrayList<Tag> tags2 = p2.getTags();

        if (tags1.isEmpty() && tags2.isEmpty()) {
            return 0;
        } else if (tags1.isEmpty()) {
            return 1;
        } else if (tags2.isEmpty()) {
            return -1;
        }

        //Treats the priority of a person's tags as the highest priority
        //out of all their tags.
        Priority maxPrio1 = Priority.PRIORITY_4;
        Priority maxPrio2 = Priority.PRIORITY_4;
        for (Tag currTag : tags1) {
            if (maxPrio1.compareTo(currTag.getPriority()) > 0) {
                maxPrio1 = currTag.getPriority();
            }
        }
        for (Tag currTag : tags2) {
            if (maxPrio2.compareTo(currTag.getPriority()) > 0) {
                maxPrio2 = currTag.getPriority();
            }
        }

        return maxPrio1.compareTo(maxPrio2);
    }
}
