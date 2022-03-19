package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import seedu.address.model.attendance.Attendance;

/**
 * Represents a Pet's attendance history in the address book.
 * Guarantees: immutable; is always valid.
 */
public class AttendanceHashMap {
    public final HashMap<LocalDate, Attendance> attendanceHashMap;

    /**
     * Constructs an {@code AttendanceHashMap}.
     *
     * @param attendanceHashMap A valid AttendanceHashMap.
     */
    public AttendanceHashMap(HashMap<LocalDate, Attendance> attendanceHashMap) {
        requireNonNull(attendanceHashMap);
        this.attendanceHashMap = attendanceHashMap;
    }

    /**
     * Constructs an empty {@code AttendanceHashMap}.
     */
    public AttendanceHashMap() {
        this.attendanceHashMap = new HashMap<>();
    }

    /**
     * Checks if there is already an identical attendance entry in the table.
     * @param attendance the incoming attendance entry.
     * @return true if entry with same attendance already exists, false otherwise.
     */
    public boolean containsAttendance(Attendance attendance) {
        LocalDate dateKey = attendance.getAttendanceDate();

        if (!(attendanceHashMap.containsKey(dateKey))) {
            return false;
        }

        return attendanceHashMap.get(dateKey).equals(attendance);
    }

    /**
     * Adds an attendance entry into the attendance hash map.
     * @param attendance the attendance to be stored.
     * @return an edited attendance hash map with the attendance marked present or absent.
     */
    public AttendanceHashMap addAttendance(Attendance attendance) {
        attendanceHashMap.put(attendance.getAttendanceDate(), attendance);
        return this;
    }

    public AttendanceHashMap addAbsentAttendance(Attendance attendance) {
        attendanceHashMap.put(attendance.getAttendanceDate(), attendance);
        return this;
    }

    public Collection<Attendance> toCollection() {
        return attendanceHashMap.values();
    }

    @Override
    public String toString() {
        return attendanceHashMap.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AttendanceHashMap
                && attendanceHashMap.equals(((AttendanceHashMap) other).attendanceHashMap));
    }

    @Override
    public int hashCode() {
        return attendanceHashMap.hashCode();
    }
}
