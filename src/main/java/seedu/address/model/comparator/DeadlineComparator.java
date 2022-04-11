package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.person.Deadline;

public class DeadlineComparator implements Comparator<Deadline> {
    @Override
    public int compare(Deadline d1, Deadline d2) {
        return d1.compareTo(d2);
    }
}
