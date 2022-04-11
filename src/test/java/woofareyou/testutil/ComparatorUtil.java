package woofareyou.testutil;

import java.time.LocalDate;

import woofareyou.model.pet.Pet;

/**
 * A utility class to assist with the testing of PetDropOffTimeComparator and PetPickUpTimeComparator.
 */
public class ComparatorUtil {

    public static final Pet PET_WITH_MISSING_ATTENDANCE_ENTRY =
            new PetBuilder().build();

    private static final String TEST_DATE = LocalDate.now().toString();
    private static final String TEST_PICK_UP_1 = "08:30";
    private static final String TEST_DROP_OFF_1 = "21:30";
    private static final String TEST_PICK_UP_2 = "10:30";
    private static final String TEST_DROP_OFF_2 = "20:30";

    public static final Pet PET_WITH_ABSENT_ATTENDANCE_ENTRY =
            new PetBuilder().withAbsentAttendanceEntry(TEST_DATE).build();
    public static final Pet PET_WITH_PRESENT_ATTENDANCE_ENTRY =
            new PetBuilder().withPresentAttendanceEntry(TEST_DATE).build();
    public static final Pet PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1 =
            new PetBuilder().withPresentAttendanceEntry(TEST_DATE, TEST_PICK_UP_1, TEST_DROP_OFF_1).build();
    public static final Pet PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2 =
            new PetBuilder().withPresentAttendanceEntry(TEST_DATE, TEST_PICK_UP_2, TEST_DROP_OFF_2).build();
}
