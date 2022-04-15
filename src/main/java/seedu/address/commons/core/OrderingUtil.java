package seedu.address.commons.core;

import java.util.Comparator;

import seedu.address.model.entry.Entry;

public class OrderingUtil {
    public static enum Ordering {
        ASCENDING, DESCENDING
    }

    /**
     * Returns a comparator that compares {@code Entry}s based on the given {@code Ordering}.
     */
    public static <T extends Entry> Comparator<T> orderedComparator(Ordering ordering, Comparator<T> comparator) {
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
