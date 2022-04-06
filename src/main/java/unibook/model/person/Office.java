package unibook.model.person;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Professor's Office in the UniBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidOffice(String)}
 */
public class Office {
    public static final String MESSAGE_CONSTRAINTS = "Office address should be less than 20 characters and"
            + " not be blank";

    /*
     * The first character of the office must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final int NAME_MAX_LENGTH = 20;
    public final String value;

    /**
     * Constructs an {@code Office}.
     *
     * @param office A valid office.
     */
    public Office(String office) {
        requireNonNull(office);
        value = office.trim();
        checkArgument(isValidOffice(value), MESSAGE_CONSTRAINTS);
    }

    /**
     * Constructs an empty {@code Office}.
     */
    public Office() {
        value = "";
    }

    /**
     * Returns true if a given string is a valid office.
     */
    public static boolean isValidOffice(String test) {
        return test.length() > 0 && test.length() <= NAME_MAX_LENGTH;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Office // instanceof handles nulls
            && value.equals(((Office) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
