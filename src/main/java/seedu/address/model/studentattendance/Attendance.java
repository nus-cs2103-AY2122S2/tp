package seedu.address.model.studentattendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jxt00
/**
 * Represents a student's attendance for lessons in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendance(String)}
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be 0 or 1";

    public static final String VALIDATION_REGEX = "^[01]{1}$";

    private static Boolean value;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance value.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        this.value = attendance.trim().equals("1");
    }

    /**
     * Returns true if a given string is a valid attendance value.
     */
    public static boolean isValidAttendance(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
