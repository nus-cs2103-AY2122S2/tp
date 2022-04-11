package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewDate(null));
    }

    @Test
    public void constructor_invalidEInterviewDate_throwsIllegalArgumentException() {
        String interviewDate = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewDate(interviewDate));
    }

    @Test
    public void isValidInterviewDate() {
        // null date
        assertThrows(NullPointerException.class, () -> InterviewDate.isValidInterviewDate(null));

        // blank date
        assertFalse(InterviewDate.isValidInterviewDate("")); // empty string
        assertFalse(InterviewDate.isValidInterviewDate(" ")); // spaces only

        // missing parts
        assertFalse(InterviewDate.isValidInterviewDate("2021-05")); // missing date
        assertFalse(InterviewDate.isValidInterviewDate("2021-05")); // missing month
        assertFalse(InterviewDate.isValidInterviewDate("05-25")); // missing year
        assertFalse(InterviewDate.isValidInterviewDate("-2013-5-6")); // missing leading zeros
        assertFalse(InterviewDate.isValidInterviewDate("-2013-7-27")); // missing leading zeros


        // invalid parts
        assertFalse(InterviewDate.isValidInterviewDate("2022-12-50")); // date out of range
        assertFalse(InterviewDate.isValidInterviewDate("2022-15-30")); // month out of range
        assertFalse(InterviewDate.isValidInterviewDate("2022-02-30")); // invalid date
        assertFalse(InterviewDate.isValidInterviewDate("2022-02-31")); // invalid date
        assertFalse(InterviewDate.isValidInterviewDate("2017-02-29")); // invalid date

        // valid parts
        assertTrue(InterviewDate.isValidInterviewDate("2022-01-24"));
        assertTrue(InterviewDate.isValidInterviewDate("2021-02-23"));
        assertTrue(InterviewDate.isValidInterviewDate("2020-03-01"));
        assertTrue(InterviewDate.isValidInterviewDate("2018-07-31"));
        assertTrue(InterviewDate.isValidInterviewDate("2017-02-28"));
    }
}
