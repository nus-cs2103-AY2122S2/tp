package seedu.address.logic.commands.sort;

import static java.util.Objects.requireNonNull;

/**
 * Sort orders that can be used for sorting tasks.
 */
public enum SortOrder {

    ASCENDING("asc"),
    DESCENDING("desc");

    public static final String MESSAGE_CONSTRAINTS =
            "Sort order should be either one of asc or desc.";

    private final String label;

    SortOrder(String sortOrder) {
        requireNonNull(sortOrder);
        this.label = sortOrder;
    }

    /**
     * Returns true if {@code String sorOrder} is a valid {@code SortOrder}, false otherwise.
     */
    public static boolean isValidSortOrder(String sortOrder) {
        if (sortOrder == null) {
            return false;
        }

        for (SortOrder s : SortOrder.values()) {
            if (s.label.equals(sortOrder)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code SortOrder} based on its label.
     *
     * @throws IllegalArgumentException If the {@code sortOrder} is an invalid label.
     */
    public static SortOrder valueOfLabel(String sortOrder) {
        requireNonNull(sortOrder);
        for (SortOrder s : SortOrder.values()) {
            if (s.label.equals(sortOrder)) {
                return s;
            }
        }
        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
    }

    @Override
    public String toString() {
        return this.label + "ending";
    }
}
