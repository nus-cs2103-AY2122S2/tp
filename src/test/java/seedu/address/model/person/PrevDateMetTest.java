package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class PrevDateMetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrevDateMet(null));
    }

    @Test
    public void constructor_invalidPrevDateMet_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new PrevDateMet(invalidDate));
    }

    @Test
    public void constructor_invalidPrevDateMet_throwsDateTimeParserException() {
        String invalidDate = "2022-13-20";
        assertThrows(DateTimeParseException.class, () -> new PrevDateMet(invalidDate));
    }

    @Test
    void isValidPrevDateMet() {
        // null date
        assertThrows(NullPointerException.class, () -> PrevDateMet.isValidPrevDateMet(null));

        // blank date
        assertFalse(PrevDateMet.isValidPrevDateMet("")); // empty string
        assertFalse(PrevDateMet.isValidPrevDateMet(" ")); // spaces only

        // missing parts
        assertFalse(PrevDateMet.isValidPrevDateMet("-01-23")); //missing year
        assertFalse(PrevDateMet.isValidPrevDateMet("2022--23")); //missing month
        assertFalse(PrevDateMet.isValidPrevDateMet("2022-01-")); //missing day
        assertFalse(PrevDateMet.isValidPrevDateMet("2022 01 23")); //missing hyphens

        // invalid parts
        assertFalse(PrevDateMet.isValidPrevDateMet(" 2020-13-23")); // leading space
        assertFalse(PrevDateMet.isValidPrevDateMet("2020 -13-23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("2020- 13-23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("2020-13 -23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("2020-13- 23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("2020-13-23 ")); // trailing space

        // invalid format
        assertFalse(PrevDateMet.isValidPrevDateMet("20-15-2022")); // DD-MM-YYYY format
        assertFalse(PrevDateMet.isValidPrevDateMet("15-20-2022")); // MM-DD-YYYY format
        assertFalse(PrevDateMet.isValidPrevDateMet("20/15/2022")); // DD/MM/YYYY format
        assertFalse(PrevDateMet.isValidPrevDateMet("15/20/2022")); // MM/DD/YYYY format

        // valid format
        assertTrue(PrevDateMet.isValidPrevDateMet("2022-12-25")); // YYYY-MM-DD format
        assertTrue(PrevDateMet.isValidPrevDateMet("2022-12-25")); // last month
        assertTrue(PrevDateMet.isValidPrevDateMet("2022-05-31")); // last day

    }

    @Test
    void isDatePossible() {
        // valid dates
        assertTrue(PrevDateMet.isDatePossible("2022-12-25")); // YYYY-MM-DD format
        assertTrue(PrevDateMet.isDatePossible("2022-12-25")); // last month
        assertTrue(PrevDateMet.isDatePossible("2022-05-31")); // last day
        assertTrue(PrevDateMet.isDatePossible("2020-02-29")); // valid leap year

        // invalid dates
        assertThrows(DateTimeParseException.class, () -> PrevDateMet.isDatePossible("2022-02-29")); // invalid leap year
        assertThrows(DateTimeParseException.class, () -> PrevDateMet.isDatePossible("2022-13-29")); // invalid month
        assertThrows(DateTimeParseException.class, () -> PrevDateMet.isDatePossible("2022-10-34")); // invalid day

    }

    @Test
    void compare() {
        PrevDateMet oldDate = new PrevDateMet("2020-01-01");
        PrevDateMet recentDate = new PrevDateMet("2022-01-01");

        assert (recentDate.compare(oldDate) == 1); // recent date is after old date
        assert (oldDate.compare(recentDate) == -1); // old date is before recent date
        assert (oldDate.compare(oldDate) == 0); // equal date
        assert (recentDate.compare(recentDate) == 0); // equal date

    }
}
