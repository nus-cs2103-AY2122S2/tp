package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's StudentId in the TAddressBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student IDs should start with an A, "
            + "followed by 7 numbers, then end with a capital alphabet.";

    /*
     * The first character of the Student ID must be an A,
     * followed by 7 digits, then a capital alphabet.
     */
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";

    public final String id;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid Student ID.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        id = studentId;
    }

    /**
     * Returns true if a given string is a valid Student ID.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && id.equals(((StudentId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
