package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Invalid gender input. "
            + "We recommend inputting gender as Male, Female or Non-binary, "
            + "but we allow any other gender input values. "
            + "Gender parameter can take any values, and it should not be blank.";

    /*
     * The first character of the familyHistory must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ImmunizationHistory}.
     *
     * @param gender Patient's gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
