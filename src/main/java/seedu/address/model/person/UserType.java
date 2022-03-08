package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class UserType {

    public static final String VALIDATION_REGEX = "buyer|seller"; //"\\p{Alnum}+";
    public static final String MESSAGE_CONSTRAINTS = "Tag names should be " + VALIDATION_REGEX;

    public final String value;

    /**
     * Constructs a {@code Tag}.
     *
     * @param value A valid tag name.
     */
    public UserType(String value) {
        requireNonNull(value);
        checkArgument(isValidTagName(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserType // instanceof handles nulls
                && value.equals(((UserType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }

}
