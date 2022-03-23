package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventName(String)}
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS = "Name can take in at most 100 characters and it should not be "
            + "blank";

    /**
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string becomes a valid input.
     * There is also a 100 characters constraint.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{1,100}";

    public final String value;

    /**
     * Constructs an {@code Name}
     * @param name A valid event name.
     */
    public EventName(String name) {
        requireNonNull(name);
        checkArgument(isValidEventName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEventName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventName
                && value.equalsIgnoreCase(((EventName) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
