package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should only contain alphanumeric characters and underscores,"
                    + "and it should be 5 to 32 characters long.";

    /*
     * The first character of the telegram handle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]{5,32}";

    public final String handle;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param handle A valid Telegram handle.
     */
    public Telegram(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTelegram(handle), MESSAGE_CONSTRAINTS);
        this.handle = handle;
    }

    /**
     * Returns true if a given string is a valid Telegram handle.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return handle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && handle.equals(((Telegram) other).handle)); // state check
    }

    @Override
    public int hashCode() {
        return handle.hashCode();
    }

}
