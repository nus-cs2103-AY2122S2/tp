package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric "
                                                       + "characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public boolean contains(String s) {
        return fullName.toLowerCase().contains(s.toLowerCase());
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Name) {
            String currentName = fullName.toLowerCase();
            String otherName = ((Name) other).fullName.toLowerCase();
            if (currentName.contains(" ") && otherName.contains(" ")) {
                return currentName.replaceAll("\\s", "")
                        .equals(otherName.replaceAll("\\s", ""));
            } else {
                return currentName.equals(otherName);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
