package manageezpz.model.person;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank.";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Checks if a given string is a valid name.
     * @param test the name to be checked.
     * @return true if the name is valid, false otherwise.
     */
    public static boolean isValidName(String test) {
        return !test.trim().isEmpty();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
