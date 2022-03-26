package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents an attendance entry of a pet.
 *  * Guarantees: immutable; entry is valid.
 */
public abstract class AttendanceEntry {

    public static final String MESSAGE_INVALID_ISPRESENT = "The isPresent field is invalid!";

    private final LocalDate attendanceDate;

    /**
     * Constructs an {@code AttendanceEntry}.
     * @param attendanceDate A valid date.
     */
    public AttendanceEntry(LocalDate attendanceDate) {
        requireNonNull(attendanceDate);
        this.attendanceDate = attendanceDate;
    }

    /**
     * Retrieves the date of the attendance entry.
     * @return A {@code LocalDate} object representing the date of the attendance entry.
     */
    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    /**
     * Retrieves the attendance status of the pet.
     * @return An optional containing the attendance status of the pet.
     */
    public abstract Optional<Boolean> getIsPresent();

    /**
     * Retrieves the pick-up time of the pet, if any.
     * @return An {@code Optional<LocalTime>} object that contains the pick-up time.
     */
    public abstract Optional<LocalTime> getPickUpTime();

    /**
     * Retrieves the drop-off time of the pet, if any.
     * @return An {@code Optional<LocalTime>} object that contains the drop-off time.
     */
    public abstract Optional<LocalTime> getDropOffTime();

    public abstract boolean hasTransportArrangement();

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return attendanceDate.hashCode();
    }

}
