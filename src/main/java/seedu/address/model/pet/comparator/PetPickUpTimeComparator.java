package seedu.address.model.pet.comparator;

import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.Pet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * Comparator class to compare pets based on pet's Pick Up Time.
 */
public class PetPickUpTimeComparator implements Comparator<Pet> {
    LocalDate today = LocalDate.now();

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
            boolean isFirstPickUpTimePresent = firstAttendanceEntry.getPickUpTime().isPresent();
            boolean isSecondPickUpTimePresent = secondAttendanceEntry.getPickUpTime().isPresent();

            if (isFirstPickUpTimePresent && isSecondPickUpTimePresent) {
                LocalTime firstPickUpTime = firstAttendanceEntry.getPickUpTime().get();
                LocalTime secondPickUpTime = secondAttendanceEntry.getPickUpTime().get();
                return firstPickUpTime.compareTo(secondPickUpTime);
            } else if (isFirstPickUpTimePresent && !isSecondPickUpTimePresent) {
                return 1;
            } else if (!isFirstPickUpTimePresent && isSecondPickUpTimePresent) {
                return -1;
            } else {
                return 0;
            }
        } else if (isFirstPresent && !isSecondPresent) {
            return -1;
        } else if (!isFirstPresent && isSecondPresent) {
            return 1;
        } else {
            return 0;
        }
    }
}
