package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's additional information in HustleBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidInfo(String)}
 */
public class Info {

    public static final String MESSAGE_CONSTRAINTS =
            "Information about clients should not be empty";
    public static final String VALIDATION_REGEX = ".*";
    public final String value;

    /**
     * Constructs a {@code Information}.
     *
     * @param info A valid information.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        value = info;
    }

    /**
     * Returns true if a given string is valid information.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && value.equals(((Info) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
