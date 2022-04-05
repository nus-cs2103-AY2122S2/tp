package seedu.address.model.studentattendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jxt00
/**
 * Represents a student's attendance for lessons in the TAssist.
 * Guarantees: immutable;
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "Attendance should only be false or true";
    public final Boolean value;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance value.
     */
    public Attendance(Boolean attendance) {
        requireNonNull(attendance);
        this.value = attendance;
    }

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance string boolean.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance.toLowerCase()), MESSAGE_CONSTRAINTS);
        this.value = Boolean.valueOf(attendance);
    }

    /**
     * Returns true if a given string is a valid boolean.
     */
    public static boolean isValidAttendance(String attendance) {
        if (attendance.equals("false") || attendance.equals("true")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Attendance
                && value.equals(((Attendance) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
