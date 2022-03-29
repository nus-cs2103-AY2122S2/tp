package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Builds a string representation of an ObservableList.
     */
    public static <T> String observableListToIndexedList(ObservableList<T> items) {
        String EMPTY_LIST_MESSAGE = "No record found, please add some!\n";
        if (items.size() == 0) return EMPTY_LIST_MESSAGE;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, items.get(i)));
        }
        return result.toString();
    }
}
