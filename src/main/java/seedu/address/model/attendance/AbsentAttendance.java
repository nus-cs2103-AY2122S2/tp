package seedu.address.model.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class AbsentAttendance extends Attendance {
    public AbsentAttendance(LocalDate attendanceDate) {
        super(attendanceDate, false);
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
