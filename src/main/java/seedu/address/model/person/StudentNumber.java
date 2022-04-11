package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's student number in the class group.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentNumber(String)}
 */
public class StudentNumber {


    public static final String MESSAGE_CONSTRAINTS =
            "Student Numbers should begin with the letter 'A', followed by 7 digits"
                    + " and finally concluded by one letter.";
    public static final String VALIDATION_REGEX = "^[aA][0-9]{7}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code StudentNumber}.
     *
     * @param studentNumber A valid student number.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        checkArgument(isValidStudentNumber(studentNumber), MESSAGE_CONSTRAINTS);
        value = studentNumber.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid student number.
     */
    public static boolean isValidStudentNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNumber // instanceof handles nulls
                && value.equals(((StudentNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
