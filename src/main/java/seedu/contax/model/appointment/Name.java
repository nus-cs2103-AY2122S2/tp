package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.AppUtil.checkArgument;

/**
 * Represents an {@link Appointment}'s name in the schedule.
 * Guarantees: Immutable; is valid declared as in {@link #isValidName(String)}.
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, spaces, the symbols .,!@#$%&*() "
                    + "and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX =
            "[\\p{Alnum}.,!@#$%&*()\\-_=+][\\p{Alnum} .,!@#$%&*()\\-_=+]*";

    public final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name, as defined in {@link #isValidName(String)}.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     * A name is considered valid if it is a non-empty string with only alphanumeric characters,
     * whitespaces, the symbols .,!@#$%&*() and does not begin with a whitespace.
     *
     * @param test The string to test.
     * @return A boolean indicating if the test string supplied is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && name.equals(((Name) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
