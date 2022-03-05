package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student Numbers should start with an A, "
            + "followed by 7 numbers, then end with an alphabet.";

    /*
     * The first character of the Student ID must be an A,
     * followed by 7 digits, then an alphabet.
     */
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";

    public final String value;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid student number.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid student number.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
