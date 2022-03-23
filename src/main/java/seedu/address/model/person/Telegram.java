package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramId(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String id;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param id A valid telegram id.
     */
    public Telegram(String id) {
        requireNonNull(id);
        checkArgument(isValidTelegramId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTelegramId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricCard // instanceof handles nulls
                && id.equals(((MatricCard) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}