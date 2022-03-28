package seedu.address.model.pet.comparator;

import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.Pet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * Comparator class to compare pets based on pet's Drop Off Time.
 */
public class PetDropOffTimeComparator implements Comparator<Pet> {
    LocalDate today = LocalDate.now();

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
            boolean isFirstDropOffTimePresent = firstAttendanceEntry.getDropOffTime().isPresent();
            boolean isSecondDropOffTimePresent = secondAttendanceEntry.getDropOffTime().isPresent();

            if (isFirstDropOffTimePresent && isSecondDropOffTimePresent) {
                LocalTime firstDropOffTime = firstAttendanceEntry.getDropOffTime().get();
                LocalTime secondDropOffTime = secondAttendanceEntry.getDropOffTime().get();
                return firstDropOffTime.compareTo(secondDropOffTime);
            } else if (isFirstDropOffTimePresent && !isSecondDropOffTimePresent) {
                return 1;
            } else if (!isFirstDropOffTimePresent && isSecondDropOffTimePresent) {
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
