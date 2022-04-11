package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateAppliedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateApplied(null));
    }

    @Test
    public void constructor_invalidDateApplied_throwsIllegalArgumentException() {
        String dateApplied = "";
        assertThrows(IllegalArgumentException.class, () -> new DateApplied(dateApplied));
    }

    @Test
    void isValidDateApplied() {
        // null date
        assertThrows(NullPointerException.class, () -> DateApplied.isValidDateApplied(null));

        // blank date
        assertFalse(DateApplied.isValidDateApplied("")); // empty string
        assertFalse(DateApplied.isValidDateApplied(" ")); // spaces only

        // missing parts
        assertFalse(DateApplied.isValidDateApplied("2021-05")); // missing date
        assertFalse(DateApplied.isValidDateApplied("2021-05")); // missing month
        assertFalse(DateApplied.isValidDateApplied("05-25")); // missing year
        assertFalse(DateApplied.isValidDateApplied("-2013-5-6")); // missing leading zeros
        assertFalse(DateApplied.isValidDateApplied("-2013-7-27")); // missing leading zeros


        // invalid parts
        assertFalse(DateApplied.isValidDateApplied("2022-12-50")); // date out of range
        assertFalse(DateApplied.isValidDateApplied("2022-15-30")); // month out of range
        assertFalse(DateApplied.isValidDateApplied("2017-02-29")); // 2017 not leap year
        assertFalse(DateApplied.isValidDateApplied("2017-02-31")); // 2017 not leap year

        // valid parts
        assertTrue(DateApplied.isValidDateApplied("2022-01-24"));
        assertTrue(DateApplied.isValidDateApplied("2021-02-23"));
        assertTrue(DateApplied.isValidDateApplied("2020-03-01"));
        assertTrue(DateApplied.isValidDateApplied("2018-07-31"));
        assertTrue(DateApplied.isValidDateApplied("2017-02-28"));
    }
}

