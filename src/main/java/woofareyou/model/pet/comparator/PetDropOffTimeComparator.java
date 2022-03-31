package woofareyou.model.pet.comparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;

import woofareyou.model.attendance.AttendanceEntry;
import woofareyou.model.pet.Pet;

/**
 * Comparator class to compare pets based on pet's Drop Off Time.
 */
public class PetDropOffTimeComparator implements Comparator<Pet> {
    private LocalDate today = LocalDate.now();

    /**
     * Compares pet objects based on their drop off time attribute.
     * @param first Pet to be compared with.
     * @param second Pet to be compared with.
     * @return Value signifying in the difference between the comparing attribute.
     */
    @Override
    public int compare(Pet first, Pet second) {
        Optional<AttendanceEntry> firstAttendanceEntry = first.getAttendanceHashMap().getAttendance(today);
        Optional<AttendanceEntry> secondAttendanceEntry = second.getAttendanceHashMap().getAttendance(today);

        if (firstAttendanceEntry.isPresent() && secondAttendanceEntry.isPresent()) {
            return compareAttendanceEntries(firstAttendanceEntry.get(), secondAttendanceEntry.get());
        } else if (firstAttendanceEntry.isPresent() && !secondAttendanceEntry.isPresent()) {
            return -1;
        } else if (!firstAttendanceEntry.isPresent() && secondAttendanceEntry.isPresent()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares AttendanceEntries of pets based on their drop off time. Used in compare method.
     * @param firstPetAttendanceEntry AttendanceEntry of first pet.
     * @param secondPetAttendanceEntry AttendanceEntry of second pet.
     * @return Value signifying in the difference between the comparing attribute.
     */
    private int compareAttendanceEntries(AttendanceEntry firstPetAttendanceEntry,
                                         AttendanceEntry secondPetAttendanceEntry) {

        boolean isFirstPetDropOffTimePresent = firstPetAttendanceEntry.hasTransportArrangement();
        boolean isSecondPetDropOffTimePresent = secondPetAttendanceEntry.hasTransportArrangement();

        if (isFirstPetDropOffTimePresent && isSecondPetDropOffTimePresent) {
            LocalTime firstDropOffTime = firstPetAttendanceEntry.getDropOffTime().get();
            LocalTime secondDropOffTime = secondPetAttendanceEntry.getDropOffTime().get();
            return firstDropOffTime.compareTo(secondDropOffTime);
        } else if (isFirstPetDropOffTimePresent && !isSecondPetDropOffTimePresent) {
            return -1;
        } else if (!isFirstPetDropOffTimePresent && isSecondPetDropOffTimePresent) {
            return 1;
        } else {
            return 0;
        }
    }

}
