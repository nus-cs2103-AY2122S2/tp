package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class MeetingDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingDate(null));
    }

    @Test
    public void constructor_invalidMeetingDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingDate(invalidDate));
    }

    @Test
    public void constructor_invalidMeetingDate_throwsDateTimeParserException() {
        String invalidDate = "2022-13-20";
        assertThrows(DateTimeParseException.class, () -> new MeetingDate(invalidDate));
    }

    @Test
    void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> MeetingDate.isValidDate(null));

        // blank date
        assertFalse(MeetingDate.isValidDate("")); // empty string
        assertFalse(MeetingDate.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(MeetingDate.isValidDate("-01-23")); //missing year
        assertFalse(MeetingDate.isValidDate("2022--23")); //missing month
        assertFalse(MeetingDate.isValidDate("2022-01-")); //missing day
        assertFalse(MeetingDate.isValidDate("2022 01 23")); //missing hyphens

        // invalid parts
        assertFalse(MeetingDate.isValidDate(" 2020-13-23")); // leading space
        assertFalse(MeetingDate.isValidDate("2020 -13-23")); // invalid space
        assertFalse(MeetingDate.isValidDate("2020- 13-23")); // invalid space
        assertFalse(MeetingDate.isValidDate("2020-13 -23")); // invalid space
        assertFalse(MeetingDate.isValidDate("2020-13- 23")); // invalid space
        assertFalse(MeetingDate.isValidDate("2020-13-23 ")); // trailing space

        // invalid format
        assertFalse(MeetingDate.isValidDate("20-15-2022")); // DD-MM-YYYY format
        assertFalse(MeetingDate.isValidDate("15-20-2022")); // MM-DD-YYYY format
        assertFalse(MeetingDate.isValidDate("20/15/2022")); // DD/MM/YYYY format
        assertFalse(MeetingDate.isValidDate("15/20/2022")); // MM/DD/YYYY format

        // valid format
        assertTrue(MeetingDate.isValidDate("2022-12-25")); // YYYY-MM-DD format
        assertTrue(MeetingDate.isValidDate("2022-12-25")); // last month
        assertTrue(MeetingDate.isValidDate("2022-05-31")); // last day
    }

    @Test
    void isDatePossible() {
        // valid dates
        assertTrue(MeetingDate.isDatePossible("2022-12-25")); // YYYY-MM-DD format
        assertTrue(MeetingDate.isDatePossible("2022-12-25")); // last month
        assertTrue(MeetingDate.isDatePossible("2022-05-31")); // last day
        assertTrue(MeetingDate.isDatePossible("2020-02-29")); // valid leap year

        // invalid dates
        assertThrows(DateTimeParseException.class, () -> MeetingDate.isDatePossible("2022-02-29")); // invalid leap year
        assertThrows(DateTimeParseException.class, () -> MeetingDate.isDatePossible("2022-13-29")); // invalid month
        assertThrows(DateTimeParseException.class, () -> MeetingDate.isDatePossible("2022-10-34")); // invalid day
    }

    @Test
    void compare() {
        MeetingDate oldDate = new MeetingDate("2020-01-01");
        MeetingDate recentDate = new MeetingDate("2022-01-01");

        assert (recentDate.compare(oldDate) == 1); // recent date is after old date
        assert (oldDate.compare(recentDate) == -1); // old date is before recent date
        assert (oldDate.compare(oldDate) == 0); // equal date
        assert (recentDate.compare(recentDate) == 0); // equal date
    }
}
