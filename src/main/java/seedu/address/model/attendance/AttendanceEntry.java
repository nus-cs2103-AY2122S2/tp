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
    private final Boolean isPresent;

    /**
     * Constructs a {@code AttendanceEntry}.
     * @param attendanceDate A valid date.
     * @param isPresent True if the pet was present on the given date, false otherwise.
     */
    public AttendanceEntry(LocalDate attendanceDate, boolean isPresent) {
        requireNonNull(attendanceDate);
        this.attendanceDate = attendanceDate;
        this.isPresent = isPresent;
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
     * @return True if the pet was present, false otherwise.
     */
    public Boolean getIsPresent() {
        return isPresent;
    }

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

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return attendanceDate.hashCode();
    }

}
