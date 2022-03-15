package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PrevDateMetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrevDateMet(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new PrevDateMet(invalidDate));
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
        assertFalse(PrevDateMet.isValidPrevDateMet("2023-13-23")); // invalid month
        assertFalse(PrevDateMet.isValidPrevDateMet("2023-13-33")); // invalid day
        assertFalse(PrevDateMet.isValidPrevDateMet("20-13-23")); // invalid year
        assertFalse(PrevDateMet.isValidPrevDateMet(" 20-13-23")); // leading space
        assertFalse(PrevDateMet.isValidPrevDateMet("20 -13-23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("20- 13-23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("20-13 -23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("20-13- 23")); // invalid space
        assertFalse(PrevDateMet.isValidPrevDateMet("20-13-23 ")); // trailing space

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
}
