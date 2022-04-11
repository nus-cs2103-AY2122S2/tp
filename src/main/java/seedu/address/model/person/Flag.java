package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Flag {

    public static final String MESSAGE_CONSTRAINTS =
            "Flag should only be 'true' or 'false'.";
    public static final String VALIDATION_REGEX = "(true|false)";
    public static final String FLAG_INPUT_TRUE = "true";
    public static final String FLAG_INPUT_FALSE = "false";
    public final boolean isFlagged;

    /**
     * Constructs a {@code Flag} with given boolean.
     */
    public Flag(boolean bool) {
        isFlagged = bool;
    }

    /**
     * Constructs a {@code Flag} with given String boolean.
     *
     * @param input Used to flag or unflag person.
     */
    public Flag(String input) {
        requireNonNull(input);
        checkArgument(isValidFlag(input), MESSAGE_CONSTRAINTS);
        if (input.equals(FLAG_INPUT_FALSE)) {
            isFlagged = false;
        } else if (input.equals(FLAG_INPUT_TRUE)) {
            isFlagged = true;
        } else {
            throw new RuntimeException("Invalid input");
        }
    }

    /**
     * Returns true if a given string is a valid flag.
     */
    public static boolean isValidFlag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return isFlagged ? "true" : "false";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Flag // instanceof handles nulls
                && isFlagged == ((Flag) other).isFlagged); // state check
    }
}
