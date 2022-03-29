package seedu.address.model.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents a missing attendance entry of a pet.
 * * Guarantees: immutable; entry is valid.
 */
public class MissingAttendanceEntry extends AttendanceEntry {
    public MissingAttendanceEntry(LocalDate attendanceDate) {
        super(attendanceDate);
    }

    @Override
    public Optional<Boolean> getIsPresent() {
        return Optional.empty();
    }

    @Override
    public Optional<LocalTime> getPickUpTime() {
        return Optional.empty();
    }

    @Override
    public Optional<LocalTime> getDropOffTime() {
        return Optional.empty();
    }

    @Override
    public boolean hasTransportArrangement() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof MissingAttendanceEntry)) {
            return false; // instanceof handles nulls
        }

        MissingAttendanceEntry otherAttendanceEntry = (MissingAttendanceEntry) other;
        return super.getAttendanceDate().equals(otherAttendanceEntry.getAttendanceDate());
    }
}
