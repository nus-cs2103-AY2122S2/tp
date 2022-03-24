package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;



public class Attendance {

    public final HashMap<LocalDate, List<LocalTime>> petAttendances;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param attendance Hashmap of valid attendance.
     */
    public Attendance(HashMap<LocalDate, List<LocalTime>> attendance) {
        requireNonNull(attendance);
        this.petAttendances = attendance;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append("{ \n");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (LocalDate attendanceDate: petAttendances.keySet()) {
            String date = attendanceDate.format(dateFormatter);
            List<LocalTime> timings = petAttendances.get(attendanceDate);
            String pickUpTime = timings.get(0).format(timeFormatter);
            String dropOffTime = timings.get(1).format(timeFormatter);
            value.append(date).append(": ").append(pickUpTime)
                    .append(dropOffTime).append(" \n");
        }
        value.append("}");
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Attendance
                && petAttendances.equals(((Attendance) other).petAttendances));
    }

    @Override
    public int hashCode() {
        return petAttendances.hashCode();
    }
}
