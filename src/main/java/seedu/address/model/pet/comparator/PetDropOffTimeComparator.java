package seedu.address.model.pet.comparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.Pet;

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
     * Compares presentAttendanceEntries of pets based on their drop off time. Used in compare method.
     * @param firstPet AttendanceEntry.
     * @param secondPet AttendanceEntry.
     * @return Value signifying in the difference between the comparing attribute.
     */
    private int comparePresentAttendanceEntries(AttendanceEntry firstPet, AttendanceEntry secondPet) {
        boolean isFirstDropOffTimePresent = firstPet.getDropOffTime().isPresent();
        boolean isSecondDropOffTimePresent = secondPet.getDropOffTime().isPresent();

        if (isFirstDropOffTimePresent && isSecondDropOffTimePresent) {
            LocalTime firstDropOffTime = firstPet.getDropOffTime().get();
            LocalTime secondDropOffTime = secondPet.getDropOffTime().get();
            return firstDropOffTime.compareTo(secondDropOffTime);
        } else if (isFirstDropOffTimePresent && !isSecondDropOffTimePresent) {
            return 1;
        } else if (!isFirstDropOffTimePresent && isSecondDropOffTimePresent) {
            return -1;
        } else {
            return 0;
        }
    }

}
