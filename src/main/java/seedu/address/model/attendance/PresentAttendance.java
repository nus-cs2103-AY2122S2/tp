package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.swing.text.html.Option;

public class PresentAttendance extends Attendance {
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;

    public PresentAttendance(LocalDate attendanceDate, LocalTime pickUpTime, LocalTime dropOffTime) {
        super(attendanceDate, true);

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
     * @param pickUpTime the pick up time of the pet.
     * @param dropOffTime the drop off time of the pet.
     * @return true if the pick up time is before the drop off time, false otherwise.
     */
    public static boolean isValidInterval(LocalTime pickUpTime, LocalTime dropOffTime) {
        return pickUpTime.isBefore(dropOffTime);
    }
}
