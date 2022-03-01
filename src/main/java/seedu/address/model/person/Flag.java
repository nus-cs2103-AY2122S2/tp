package seedu.address.model.person;

public class Flag {

    public static final String MESSAGE_CONSTRAINTS =
            "Flag should only be 'true' or 'false'.";
    public static final String VALIDATION_REGEX = "(true|false)";
    public boolean isFlagged;

    /**
     * Constructs a false {@code Flag}.
     */
    public Flag() {
        isFlagged = false;
    }

    /**
     * Constructs a {@code Flag} with a boolean.
     *
     * @param bool If the person is flagged.
     */
    public Flag(String bool) {
        if (bool.equals("false")) {
            isFlagged = false;
        } else if (bool.toLowerCase().equals("true")) {
            isFlagged = true;
        } else {
            throw new RuntimeException("invalid bool");
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidFlag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return isFlagged ? "Flagged" : "Unflagged";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Flag // instanceof handles nulls
                && isFlagged == ((Flag) other).isFlagged); // state check
    }
}
