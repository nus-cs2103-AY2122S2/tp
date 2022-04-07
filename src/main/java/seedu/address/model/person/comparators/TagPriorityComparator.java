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
        boolean hasNonNull1 = false;
        boolean hasNull1 = false;
        Priority maxPrio2 = Priority.PRIORITY_4;
        boolean hasNonNull2 = false;
        boolean hasNull2 = false;

        for (Tag currTag : tags1) {
            Priority currPrio = currTag.getPriority();
            if (currPrio == null) {
                hasNull1 = true;
                continue;
            }

            hasNonNull1 = true;
            if (maxPrio1.compareTo(currPrio) > 0) {
                maxPrio1 = currPrio;
            }
        }

        for (Tag currTag : tags2) {
            Priority currPrio = currTag.getPriority();
            if (currPrio == null) {
                hasNull2 = true;
                continue;
            }

            hasNonNull2 = true;
            if (maxPrio2.compareTo(currPrio) > 0) {
                maxPrio2 = currPrio;
            }
        }

        boolean isNull1 = hasNull1 && !hasNonNull1;
        boolean isNull2 = hasNull2 && !hasNonNull2;

        if (isNull1 && isNull2) {
            return 0;
        } else if (isNull1) {
            return 1;
        } else if (isNull2) {
            return -1;
        } else {
            return maxPrio1.compareTo(maxPrio2);
        }
    }
}
