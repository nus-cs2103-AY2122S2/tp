package seedu.trackermon.model.show;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.util.AppUtil.checkArgument;

/**
 * Represents the Name of the Show.
 * Guarantees: immutable; name is valid as declared in {@link #isValidName(String)}.
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Name should only contain up to 500 alphanumeric characters and spaces, it should also not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,499}";

    public final String name;

    /**
     * Constructs a {@code Name} with the provided {@code String}.
     * @param name {@code String}
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given {@code String} is a valid name.
     * @param test provided {@code String}.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Return {@code String} representation of {@code Name}.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns whether two objects are equal, or share the same name.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && name.equalsIgnoreCase(((Name) other).name)); // state check
    }

    /**
     * Returns the hashcode of the {@code Name}.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Name other) {
        return this.name.toUpperCase().compareTo(other.name.toUpperCase());
    }
}

