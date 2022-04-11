package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Recipe's name in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should contain valid alpha-numeric characters.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        name = name.trim();
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Checks the validity of the input string for {@code Name}. <br>
     * Validity: Input is not empty (trim would strip whitespace).
     * @return true if input string is not empty
     */
    public static boolean isValidName(String test) {
        // if test = " ", test.strip = ""
        boolean isNotEmpty = !test.isEmpty();
        boolean hasNoSpecialCharacters = StringUtil.isAlphaNumeric(test);
        return isNotEmpty && hasNoSpecialCharacters;
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Name)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Name other = (Name) o;
        return this.fullName.equalsIgnoreCase(other.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
