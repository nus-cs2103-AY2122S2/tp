package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment's simple name in the TAssist.
 * It is used for easy typing if the user choose to have a shorter name compared to the actual assessment name.
 * Guarantees: immutable; is valid as declared in {@link #isValidSimpleName(String)}
 */
public class SimpleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Simple name should only contain alphanumeric characters and no spaces";

    /*
     * The first character of the Simple Name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}]*";

    public final String value;

    /**
     * Constructs a {@code SimpleName}.
     *
     * @param simpleName A valid simple name.
     */
    public SimpleName(String simpleName) {
        requireNonNull(simpleName);
        checkArgument(isValidSimpleName(simpleName), MESSAGE_CONSTRAINTS);
        value = simpleName;
    }

    /**
     * Returns true if a given string is a valid simple name.
     */
    public static boolean isValidSimpleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SimpleName // instanceof handles nulls
                && value.equals(((SimpleName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
