package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.common.Name;

/**
 * Represents a Person's log name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLogName(String)}
 */
public class LogName extends Name {

    public static final int TITLE_LENGTH_CONSTRAINT = 50;

    public static final String MESSAGE_CONSTRAINTS = "Titles of logs must satisfy:\n"
                    + "1. Not be trivial (i.e. not empty or contain only spaces)\n"
                    + "2. Be at most " + TITLE_LENGTH_CONSTRAINT + " characters long. "
                    + "This is because of display limitations.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Graph}][\\p{Alnum}\\p{Punct}\\s]{0," + (TITLE_LENGTH_CONSTRAINT - 1) + "}";

    /**
     * Constructs a {@code FriendName}.
     *
     * @param name A valid name.
     */
    public LogName(String name) {
        super(name);
        checkArgument(isValidLogName(name), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidLogName(String test) {
        // Ensure in implementation Regex is not null
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogName // instanceof handles nulls
                && fullName.equals(((LogName) other).fullName)); // state check
    }
}
