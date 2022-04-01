package seedu.address.commons.core;

import java.util.Comparator;

import seedu.address.model.entry.Entry;

public class OrderingUtil {
    public static enum Ordering {
        ASCENDING, DESCENDING
    }

    public static Comparator<? extends Entry> orderedComparator(Ordering ordering, Comparator<? extends Entry> comparator) {
        switch (ordering) {
        case ASCENDING:
            return comparator;
        case DESCENDING:
            return comparator.reversed();
        default:
            throw new IllegalArgumentException("Unknown ordering: " + ordering);
        }
    }
}
