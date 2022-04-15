package seedu.address.logic.commands.sort;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.task.Task;

/**
 * Creates a {@code Comparator<Task>} to sort tasks.
 */
public class ComparatorFactory {

    public static final Comparator<Task> COMPARATOR_SORT_DEADLINE_ASCENDING =
            createComparator(SortKey.DEADLINE, SortOrder.ASCENDING);

    /**
     * Returns a {@code Comparator<Task>} using the specified {@SortKey}  and its natural order.
     */
    private static Comparator<Task> createComparatorOnSortKey(SortKey sortKey) {
        switch (sortKey) {
        case NAME:
            return Comparator.comparing(Task::getName).thenComparing(Task::getDeadline);

        case DEADLINE:
            return Comparator.comparing(Task::getDeadline).thenComparing(Task::getName);

        case PRIORITY:
            return Comparator.comparing(Task::getPriority).thenComparing(Task::getName);

        default:
            throw new IllegalArgumentException(SortKey.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns a {@code Comparator<Task>} using the specified {@code SortKey} and {@code SortOrder}.
     */
    public static Comparator<Task> createComparator(SortKey sortKey, SortOrder sortOrder) {
        requireAllNonNull(sortKey, sortOrder);
        Comparator<Task> comparator = createComparatorOnSortKey(sortKey);

        switch (sortOrder) {
        case ASCENDING:
            return comparator;

        case DESCENDING:
            return comparator.reversed();

        default:
            throw new IllegalArgumentException(SortOrder.MESSAGE_CONSTRAINTS);
        }
    }
}
