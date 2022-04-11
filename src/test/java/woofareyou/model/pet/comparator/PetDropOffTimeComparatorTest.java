package woofareyou.model.pet.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import woofareyou.testutil.ComparatorUtil;

public class PetDropOffTimeComparatorTest {
    private PetDropOffTimeComparator comparator = new PetDropOffTimeComparator();

    @Test
    public void compare() {

        // compare pet with itself
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 0);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1) == 0);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2) == 0);

        // compare pets without transport timing against themselves
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY) == 1);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 1);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY) == 0);

        // compare pets with transport timing against pets without transport timing
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                ComparatorUtil.PET_WITH_ABSENT_ATTENDANCE_ENTRY) == -1);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY) == -1);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                ComparatorUtil.PET_WITH_MISSING_ATTENDANCE_ENTRY) == -1);

        // compare pets with different transport timing against themselves
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2) > 0);
        assertTrue(comparator.compare(ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_2,
                ComparatorUtil.PET_WITH_PRESENT_ATTENDANCE_ENTRY_AND_TRANSPORT_TIMING_1) < 0);
    }
}
