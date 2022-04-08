package manageezpz.model.task;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.AppUtil.checkArgument;

public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description should not be blank!";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Checks if a given string is a valid description.
     * @param test the description to be checked.
     * @return true if a given string is a valid description, false otherwise.
     */
    public static boolean isValidDescription(String test) {
        return !(test.trim().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
