package woofareyou.model.pet.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.testutil.ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY;
import static woofareyou.testutil.ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY;
import static woofareyou.testutil.ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY;
import static woofareyou.testutil.ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1;
import static woofareyou.testutil.ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2;

import org.junit.jupiter.api.Test;

public class PetPickUpTimeComparatorTest {
    private PetPickUpTimeComparator comparator = new PetPickUpTimeComparator();

    @Test
    public void compare() {

        // compare pet with itself
        assertTrue(comparator.compare(PET_WITH_ABSENT_ATTENDANCE_ENTRY,
                PET_WITH_ABSENT_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(PET_WITH_MISSING_ATTENDANCE_ENTRY,
                PET_WITH_MISSING_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1) == 0);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2) == 0);

        // compare pets without transport timing against themselves
        assertTrue(comparator.compare(PET_WITH_MISSING_ATTENDANCE_ENTRY,
                PET_WITH_ABSENT_ATTENDANCE_ENTRY) == 1);
        assertTrue(comparator.compare(PET_WITH_MISSING_ATTENDANCE_ENTRY,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 1);
        assertTrue(comparator.compare(PET_WITH_ABSENT_ATTENDANCE_ENTRY,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 0);

        // compare pets with transport timing against pets without transport timi
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                PET_WITH_ABSENT_ATTENDANCE_ENTRY) == -1);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY) == -1);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                PET_WITH_MISSING_ATTENDANCE_ENTRY) == -1);

        // compare pets with different transport timing against themselves
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2) < 0);
        assertTrue(comparator.compare(PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2,
                PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1) > 0);

    }
}
