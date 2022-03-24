package seedu.trackbeau.model.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Service's name in trackBeau.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ServiceName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, hyphens and spaces, and it should not be blank";

    /*
     * The first character of name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} -]*";

    public final String fullName;

    /**
     * Constructs a {@code ServiceName}.
     *
     * @param name A valid name.
     */
    public ServiceName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ServiceName // instanceof handles nulls
                && fullName.equals(((ServiceName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
