package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author wxliong
/**
 * Represents a Person's Telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {


    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle should only contain letters, 0-9, and underscores."
                    + "\nIt should be at least 5 characters long";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]{5,}$";
    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid Telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns true if a given string is a valid Telegram handle.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

