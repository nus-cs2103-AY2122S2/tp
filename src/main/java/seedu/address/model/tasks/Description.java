package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's Deadline in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDesc(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[.*\\S.*].*";

    public final String desc;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDesc(description), MESSAGE_CONSTRAINTS);
        desc = description.trim();
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDesc(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return desc;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && desc.equals(((Description) other).desc)); // state check
    }

    @Override
    public int hashCode() {
        return desc.hashCode();
    }

}
