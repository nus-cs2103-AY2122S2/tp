package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's matriculation number in Tracey.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatriculationNumber(String)}
 */
public class MatriculationNumber {


    public static final String MESSAGE_CONSTRAINTS =
            "Matriculation number should be in the following format: A1234567X, where X is a placeholder for the "
                    + "last alphabet of the student's matriculation number, and it should not be blank.";

    /*
     * The first character of the matriculation number must be an A.
     * The matriculation number must contain exactly 7 numbers in between two characters.
     * The last character can be any alphabet.
     */
    public static final String VALIDATION_REGEX = "[aA][\\d]{7}[\\p{Alpha}]";

    public final String value;

    /**
     * Constructs a {@code MatriculationNumber}.
     *
     * @param number A valid matriculation number.
     */
    public MatriculationNumber(String number) {
        requireNonNull(number);
        checkArgument(isValidMatriculationNumber(number), MESSAGE_CONSTRAINTS);
        value = number.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     *
     * @param test string to be tested to determine if valid matriculation number.
     * @return boolean where true if a given string is a valid number, false otherwise.
     */
    public static boolean isValidMatriculationNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumber // instanceof handles nulls
                && value.equals(((MatriculationNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
