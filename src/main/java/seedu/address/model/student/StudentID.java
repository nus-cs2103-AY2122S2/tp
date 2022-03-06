package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's studentID in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentID(String)}
 */
public class StudentID {

    public static final String MESSAGE_CONSTRAINTS = "StudentID can take any values except special characters"
           + ", and it should not be blank";

    /*
     * The first character of the studentID must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[Ee][0-9]{0,7}$";

    public final String value;

    /**
     * Constructs an {@code StudentID}.
     *
     * @param studentID A valid studentID.
     */
    public StudentID(String studentID) {
        requireNonNull(studentID);
        checkArgument(isValidStudentID(studentID), MESSAGE_CONSTRAINTS);
        value = studentID;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidStudentID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentID // instanceof handles nulls
                && value.equals(((StudentID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
