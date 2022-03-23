package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import seedu.address.model.attendance.AttendanceEntry;

/**
 * Represents a Pet's attendance history in the address book.
 * Guarantees: immutable; is always valid.
 */
public class AttendanceHashMap {
    public final HashMap<LocalDate, AttendanceEntry> attendanceHashMap;

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
     * @return true if entry with same absent attendance already exists, false otherwise.
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
     * Checks if attendance has already been marked on a given date.
     * @param date the target date.
     * @return true if attendance has been marked, false otherwise.
     */
    public boolean hasAttendanceEntry(LocalDate date) {
        return attendanceHashMap.containsKey(date);
    }

    /**
     * Checks if the attendance hash map is empty.
     * @return true if attendance hash map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return attendanceHashMap.isEmpty();
    }

    /**
     * Adds an attendance entry into a new attendance hash map containing the current attendance entries.
     * @param attendanceEntry the attendance to be stored.
     * @return an attendance hash map with the attendance marked present or absent.
     */
    public AttendanceHashMap addAttendance(AttendanceEntry attendanceEntry) {
        HashMap<LocalDate, AttendanceEntry> tempHashMap = new HashMap<>(attendanceHashMap);
        tempHashMap.put(attendanceEntry.getAttendanceDate(), attendanceEntry);
        return new AttendanceHashMap(tempHashMap);
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
