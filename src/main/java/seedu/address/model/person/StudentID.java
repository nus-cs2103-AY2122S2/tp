package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentID {
    public static final String MESSAGE_CONSTRAINTS =
            "IDs should only contain alphanumeric characters, and it should not be blank";

    /*
     * The format of Student ID should be E followed by 7 digits without spacing.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Digit}]{7}";

    public final String studentID;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param id A valid student ID.
     */
    public StudentID(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        studentID = id;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return studentID;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentID // instanceof handles nulls
                && studentID.equals(((StudentID) other).studentID)); // state check
    }

    @Override
    public int hashCode() {
        return studentID.hashCode();
    }
}
