package seedu.address.model.pet.comparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.Pet;

/**
 * Comparator class to compare pets based on pet's Pick Up Time.
 */
public class PetPickUpTimeComparator implements Comparator<Pet> {
    private LocalDate today = LocalDate.now();

    /**
     * Compares pet objects based on their pick up time attribute.
     * @param first Pet to be compared with.
     * @param second Pet to be compared with.
     * @return Value signifying in the difference between the comparing attribute.
     */
    @Override
    public int compare(Pet first, Pet second) {
        AttendanceEntry firstAttendanceEntry = first.getAttendanceHashMap().getAttendanceEntry(today);
        AttendanceEntry secondAttendanceEntry = second.getAttendanceHashMap().getAttendanceEntry(today);

        boolean isFirstPresent = firstAttendanceEntry instanceof PresentAttendanceEntry;
        boolean isSecondPresent = secondAttendanceEntry instanceof PresentAttendanceEntry;

        if (isFirstPresent && isSecondPresent) {
            return comparePresentAttendanceEntries(firstAttendanceEntry, secondAttendanceEntry);
        } else if (isFirstPresent && !isSecondPresent) {
            return -1;
        } else if (!isFirstPresent && isSecondPresent) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares presentAttendanceEntries of pets based on their drop off time.
     * Used in compare method.
     * @param firstPet's AttendanceEntry.
     * @param secondPet's AttendanceEntry.
     * @return Value signifying in the difference between the comparing attribute.
     */
    private int comparePresentAttendanceEntries(AttendanceEntry firstPet, AttendanceEntry secondPet) {
        boolean isFirstPickUpTimePresent = firstPet.getPickUpTime().isPresent();
        boolean isSecondPickUpTimePresent = secondPet.getPickUpTime().isPresent();

        if (isFirstPickUpTimePresent && isSecondPickUpTimePresent) {
            LocalTime firstPickUpTime = firstPet.getPickUpTime().get();
            LocalTime secondPickUpTime = secondPet.getPickUpTime().get();
            return firstPickUpTime.compareTo(secondPickUpTime);
        } else if (isFirstPickUpTimePresent && !isSecondPickUpTimePresent) {
            return 1;
        } else if (!isFirstPickUpTimePresent && isSecondPickUpTimePresent) {
            return -1;
        } else {
            return 0;
        }
    }
}
