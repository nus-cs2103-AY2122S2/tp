package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public abstract class Attendance {

    public static final String MESSAGE_INVALID_IS_PRESENT = "The isPresent field is invalid!";
    public static final String MESSAGE_TIME_CONSTRAINTS = "The pickup time should be before the drop off time!";

    private final LocalDate attendanceDate;
    private final Boolean isPresent;

    public Attendance(LocalDate attendanceDate, boolean isPresent) {
        requireNonNull(attendanceDate);
        this.attendanceDate = attendanceDate;
        this.isPresent = isPresent;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public abstract Optional<LocalTime> getPickUpTime();

    public abstract Optional<LocalTime> getDropOffTime();

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof Attendance)) {
            return false; // instanceof handles nulls
        }

        Attendance otherAttendance = (Attendance) other;
        return attendanceDate.equals(otherAttendance.attendanceDate)
                && isPresent.equals(otherAttendance.isPresent); // state check
    }

    @Override
    public int hashCode() {
        return attendanceDate.hashCode();
    }

}
