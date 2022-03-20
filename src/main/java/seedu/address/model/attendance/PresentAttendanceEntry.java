package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents a present attendance entry of a pet.
 *  * Guarantees: immutable; entry is valid.
 */
public class PresentAttendanceEntry extends AttendanceEntry {
    public static final String MESSAGE_TIME_CONSTRAINTS = "The pick-up time should be before the drop-off time!";

    private final LocalTime pickUpTime;
    private final LocalTime dropOffTime;

    /**
     * Constructs a {@code PresentAttendanceEntry}.
     * @param entryDate A valid date.
     * @param pickUpTime A valid pick up time.
     * @param dropOffTime A valid drop off time.
     */
    public PresentAttendanceEntry(LocalDate entryDate, LocalTime pickUpTime, LocalTime dropOffTime) {
        super(entryDate, true);

        requireNonNull(pickUpTime);
        requireNonNull(dropOffTime);

        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
    }

    @Override
    public Optional<LocalTime> getPickUpTime() {
        return Optional.of(pickUpTime);
    }

    @Override
    public Optional<LocalTime> getDropOffTime() {
        return Optional.of(dropOffTime);
    }

    /**
     * Checks to see if the pick up time is before the drop off time.
     *
     * @param pickUpTime  the pick up time of the pet.
     * @param dropOffTime the drop off time of the pet.
     * @return true if the pick up time is before the drop off time, false otherwise.
     */
    public static boolean isValidTimings(LocalTime pickUpTime, LocalTime dropOffTime) {
        return pickUpTime.isBefore(dropOffTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof PresentAttendanceEntry)) {
            return false; // instanceof handles nulls
        }

        PresentAttendanceEntry otherAttendanceEntry = (PresentAttendanceEntry) other;
        return super.getAttendanceDate().equals(otherAttendanceEntry.getAttendanceDate()) &&
                super.getIsPresent().equals(otherAttendanceEntry.getIsPresent()) &&
                pickUpTime.equals(otherAttendanceEntry.pickUpTime) &&
                dropOffTime.equals(otherAttendanceEntry.dropOffTime); // state checks
    }
}
