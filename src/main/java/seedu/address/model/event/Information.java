package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's information in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInformation(String)}
 */
public class Information {
    public static final String MESSAGE_CONSTRAINTS = "Name can take in at most 300 characters and it should not be "
            + "blank";

    /**
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string becomes a valid input.
     * There is also a 300 characters constraint.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{1,300}";

    public final String value;

    /**
     * Constructs an {@code Information}
     * @param information A valid event information.
     */
    public Information(String information) {
        requireNonNull(information);
        checkArgument(isValidInformation(information), MESSAGE_CONSTRAINTS);
        value = information;
    }

    /**
     * Returns true if a given string is a valid information.
     */
    public static boolean isValidInformation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Information
                && value.equalsIgnoreCase(((Information) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
