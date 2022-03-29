package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import seedu.address.model.attendance.AttendanceEntry;

/**
 * Represents a Pet's attendance history in the address book.
 * Guarantees: is always valid.
 */
public class AttendanceHashMap {
    private final HashMap<LocalDate, AttendanceEntry> attendanceHashMap;

    /**
     * Constructs an {@code AttendanceHashMap}.
     *
     * @param attendanceHashMap A valid AttendanceHashMap.
     */
    public AttendanceHashMap(HashMap<LocalDate, AttendanceEntry> attendanceHashMap) {
        requireNonNull(attendanceHashMap);
        this.attendanceHashMap = new HashMap<>(attendanceHashMap);
    }

    /**
     * Constructs an empty {@code AttendanceHashMap}.
     */
    public AttendanceHashMap() {
        this.attendanceHashMap = new HashMap<>();
    }

    /**
     * Checks if there is already an identical attendance entry in the table.
     * @param attendanceEntry the incoming attendance entry.
     * @return true if entry with same attendance already exists, false otherwise.
     */
    public boolean containsAttendance(AttendanceEntry attendanceEntry) {
        LocalDate dateKey = attendanceEntry.getAttendanceDate();

        if (!(attendanceHashMap.containsKey(dateKey))) {
            return false;
        }

        AttendanceEntry existingAttendanceEntry = attendanceHashMap.get(dateKey);

        return existingAttendanceEntry.equals(attendanceEntry);
    }

    /**
     * Checks if the attendance hash map is empty.
     * @return true if attendance hash map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return attendanceHashMap.isEmpty();
    }

    /**
     * Adds an attendance entry into the current attendance hash map.
     * @param attendanceEntry the attendance to be stored.
     */
    public void addAttendance(AttendanceEntry attendanceEntry) {
        attendanceHashMap.put(attendanceEntry.getAttendanceDate(), attendanceEntry);
    }

    /**
     * Retrieves the attendance entry at the specified date.
     * @param attendanceDate the specified date.
     * @return an {@code Optional} object containing the attendance entry, if any.
     */
    public Optional<AttendanceEntry> getAttendance(LocalDate attendanceDate) {
        return Optional.ofNullable(attendanceHashMap.get(attendanceDate));
    }

    /**
     * Returns the attendance hash map in collection form.
     * @return a collection containing date-attendance pairs.
     */
    public Collection<AttendanceEntry> toCollection() {
        return attendanceHashMap.values();
    }

    /**
     * Returns a copy of the current attendance hash map.
     * @return a copy of the {@code AttendanceHashMap}.
     */
    public AttendanceHashMap getCopy() {
        return new AttendanceHashMap(
            new HashMap<>(
                this.attendanceHashMap)
        );
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
