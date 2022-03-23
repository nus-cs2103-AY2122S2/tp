package seedu.address.model.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.address.commons.util.AttendanceUtil;

/**
 * Represents a present attendance entry of a pet.
 * * Guarantees: immutable; entry is valid.
 */
public class PresentAttendanceEntry extends AttendanceEntry {
    public static final String MESSAGE_TIME_CONSTRAINTS = "The pick-up time should be before the drop-off time!";
    private static final String PICKUP_STRING = "Pick-up: %1$s";
    private static final String DROPOFF_STRING = "Drop-off: %1$s";
    private static final String NO_PICKUP_DROPOFF_STRING = "No pick-up and drop-off times specified.";

    private final Boolean isPresent;
    private final LocalTime pickUpTime;
    private final LocalTime dropOffTime;

    /**
     * Constructs a {@code PresentAttendanceEntry}.
     *
     * @param entryDate   A valid date.
     * @param pickUpTime  A valid pick-up time.
     * @param dropOffTime A valid drop-off time.
     */
    public PresentAttendanceEntry(LocalDate entryDate, LocalTime pickUpTime, LocalTime dropOffTime) {
        super(entryDate);

        this.isPresent = true;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
    }

    @Override
    public Optional<Boolean> getIsPresent() {
        return Optional.ofNullable(isPresent);
    }

    @Override
    public Optional<LocalTime> getPickUpTime() {
        return Optional.ofNullable(pickUpTime);
    }

    @Override
    public Optional<LocalTime> getDropOffTime() {
        return Optional.ofNullable(dropOffTime);
    }

    /**
     * Checks if both pick-up and drop-off times have been specified.
     * @return true if both specified, false otherwise.
     */
    private boolean hasPickUpDropOff() {
        return getPickUpTime().isPresent() && getDropOffTime().isPresent();
    }

    /**
     * Checks if either only pick-up or only drop-off time has been specified.
     * @return true if one and only one specified, false otherwise
     */
    private boolean onlyOnlyTimePresent() {
        return getPickUpTime().isPresent() ^ getDropOffTime().isPresent();
    }

    /**
     * Checks to see if the timings in the entry are valid.
     * A valid timing is when either both timings are null,
     * or both timings are present, with the pick-up time before the drop-off time.
     *
     * @return true if the timings are valid, false otherwise.
     */
    public boolean isValidTimings() {
        if (onlyOnlyTimePresent()) {
            return false;
        } else if (hasPickUpDropOff()) {
            return pickUpTime.isBefore(dropOffTime);
        }

        return true; // both timings are not present
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
        return super.getAttendanceDate().equals(otherAttendanceEntry.getAttendanceDate())
            && isPresent.equals(otherAttendanceEntry.isPresent)
            && getPickUpTime().equals(otherAttendanceEntry.getPickUpTime())
            && getDropOffTime().equals(otherAttendanceEntry.getDropOffTime()); // state checks
    }

    @Override
    public String toString() {
        String date = getAttendanceDate().format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);

        if (hasPickUpDropOff()) {
            String pickUp = String.format(PICKUP_STRING, pickUpTime.toString());
            String dropOff = String.format(DROPOFF_STRING, dropOffTime.toString());
            return "Present | " + date + " | " + pickUp + " | " + dropOff;
        }

        return "Present | " + date + " | " + NO_PICKUP_DROPOFF_STRING;
    }
}
