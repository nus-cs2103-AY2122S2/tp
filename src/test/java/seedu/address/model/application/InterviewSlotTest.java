package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class InterviewSlotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewSlot(null));
    }

    @Test
    public void constructor_invalidInterviewSlot_throwsIllegalArgumentException() {
        String invalidInterviewSlot = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewSlot(invalidInterviewSlot));
    }

    @Test
    public void isValidInterviewSlot() {
        // null interview slot
        assertThrows(NullPointerException.class, () -> InterviewSlot.isValidDateTime(null));

        // blank interview slot
        assertFalse(InterviewSlot.isValidDateTime("")); // empty string
        assertFalse(InterviewSlot.isValidDateTime(" ")); // spaces only

        // missing parts
        assertFalse(InterviewSlot.isValidDateTime("30-03-2022 15:")); // missing minutes part
        assertFalse(InterviewSlot.isValidDateTime("30-03-2022 :30")); // missing hours part
        assertFalse(InterviewSlot.isValidDateTime("-02-2022 15:30")); // missing day part
        assertFalse(InterviewSlot.isValidDateTime("30--2022 15:30")); // missing month part
        assertFalse(InterviewSlot.isValidDateTime("30-03- 15:30")); // missing year part
        assertFalse(InterviewSlot.isValidDateTime("30032022 15:30")); // missing date separator
        assertFalse(InterviewSlot.isValidDateTime("30-03-2022 1530")); // missing minutes separator

        // invalid parts
        assertFalse(InterviewSlot.isValidDateTime("30-02-2022 15:30")); // invalid date
        assertFalse(InterviewSlot.isValidDateTime("30/03/2022 15:30")); // invalid date separator
        assertFalse(InterviewSlot.isValidDateTime("03-30-2022 15:30")); // invalid month
        assertFalse(InterviewSlot.isValidDateTime("32-02-2022 15:30")); // invalid date
        assertFalse(InterviewSlot.isValidDateTime("30-03-22 15:30")); // invalid year
        assertFalse(InterviewSlot.isValidDateTime("30-03-2022 15:30H")); // extra character
        assertFalse(InterviewSlot.isValidDateTime("30-03-2022 24:01")); // invalid time
        assertFalse(InterviewSlot.isValidDateTime("30-May-2022 15:30")); // invalid month

        // valid interview slot
        assertTrue(InterviewSlot.isValidDateTime("01-01-1995 00:00")); // Midnight
        assertTrue(InterviewSlot.isValidDateTime("30-03-2022 15:00")); //
    }
}
