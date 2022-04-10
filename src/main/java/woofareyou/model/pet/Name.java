package woofareyou.model.pet;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pet's name in WoofAreYou.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphabets and spaces, and should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z]+[A-Za-z ]*$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name.trim().replaceAll(" +", " ");
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
                || (other instanceof Name // instanceof handles nulls
                && fullName.equalsIgnoreCase(((Name) other).fullName));
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
     * Compare two {@code name} in lexicographical order
     * @param other {@code name} to be compared with
     * @return an Integer
     */
    @Override
    public int compareTo(Name other) {
        return Integer.compare(this.fullName.compareToIgnoreCase(other.fullName), 0);
    }
}

