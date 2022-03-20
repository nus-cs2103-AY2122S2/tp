package seedu.address.model.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents an absent attendance entry of a pet.
 *  * Guarantees: immutable; entry is valid.
 */
public class AbsentAttendanceEntry extends AttendanceEntry {

    /**
     * Constructs a {@code AbsentAttendanceEntry}.
     * @param entryDate A valid date.
     */
    public AbsentAttendanceEntry(LocalDate entryDate) {
        super(entryDate, false);
    }

    @Override
    public Optional<LocalTime> getPickUpTime() {
        return Optional.empty();
    }

    @Override
    public Optional<LocalTime> getDropOffTime() {
        return Optional.empty();
    }
}
