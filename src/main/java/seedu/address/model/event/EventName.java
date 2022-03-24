package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.common.Name;

/**
 * Represents an Event's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventName(String)}
 */
public class EventName extends Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters, "
            + "special characters and spaces, and it should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9][^\\t\\n\\r\\f]*";

    /**
     * Constructs a {@code FriendName}.
     *
     * @param name A valid name.
     */
    public EventName(String name) {
        super(name);
        checkArgument(isValidEventName(name), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEventName(String test) {
        // Ensure in implementation Regex is not null
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventName // instanceof handles nulls
                && fullName.equalsIgnoreCase(((EventName) other).fullName)); // state check
    }
}
