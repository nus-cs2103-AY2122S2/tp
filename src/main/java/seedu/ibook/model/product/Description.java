package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's description in the ibook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces";

    /*
     * Empty strings are allowed. Otherwise, the first character of the description
     * must not be a whitespace
     */
    public static final String VALIDATION_REGEX = "(.*)";

    public final String fullDescription;

    private Description() {
        fullDescription = "???";
    }

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        fullDescription = description;
    }

    /**
     * Checks if the string is valid as per {@code VALIDATION_REGEX}.
     *
     * @param test String to test.
     * @return Result of test.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.equals(((Description) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
