package seedu.address.model.person;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description can take any values, " +
            "and it should not be empty if the /d flag has been input by user";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        if (description == null) {
            value = null;
        } else {
            checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
            value = description;
        }
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Description) {
            if (value == null) {
                return Objects.isNull(((Description) other).value);
            } else if (Objects.isNull(((Description) other).value)) {
                return false;
            } else {
                return value.equals(((Description) other).value);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
