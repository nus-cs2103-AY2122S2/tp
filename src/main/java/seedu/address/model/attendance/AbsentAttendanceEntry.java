package seedu.address.model.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.address.commons.util.AttendanceUtil;

/**
 * Represents an absent attendance entry of a pet.
 *  * Guarantees: immutable; entry is valid.
 */
public class AbsentAttendanceEntry extends AttendanceEntry {
    private final Boolean isPresent;

    /**
     * Constructs a {@code AbsentAttendanceEntry}.
     * @param entryDate A valid date.
     */
    public AbsentAttendanceEntry(LocalDate entryDate) {
        super(entryDate);
        this.isPresent = false;
    }

    @Override
    public Optional<Boolean> getIsPresent() {
        return Optional.ofNullable(isPresent);
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
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof AbsentAttendanceEntry)) {
            return false; // instanceof handles nulls
        }

        AbsentAttendanceEntry otherAttendanceEntry = (AbsentAttendanceEntry) other;
        return this.getAttendanceDate().equals(otherAttendanceEntry.getAttendanceDate())
                && this.getIsPresent().equals(otherAttendanceEntry.getIsPresent()); // state check
    }

    @Override
    public String toString() {
        String date = getAttendanceDate().format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);
        return "Absent | " + date;
    }
}
