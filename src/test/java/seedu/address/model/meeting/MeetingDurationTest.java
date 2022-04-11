package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingDurationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingDuration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingDuration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration
        assertThrows(NullPointerException.class, ()->MeetingDuration.isValidDuration(null));

        // blank duration
        assertFalse(MeetingDuration.isValidDuration(""));
        assertFalse(MeetingDuration.isValidDuration("  "));

        // wrong format
        assertFalse(MeetingDuration.isValidDuration(".123"));

        // more than 4 decimals
        assertFalse(MeetingDuration.isValidDuration("12.12345"));
        assertFalse(MeetingDuration.isValidDuration("0.123456"));

        // EPs [-INFINITY ... 0.0166], [0.0167 ... 24], [24.0001 ... INFINITY]
        // EP1
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Double.NEGATIVE_INFINITY)));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MIN_VALUE)));
        assertFalse(MeetingDuration.isValidDuration("-12512"));
        assertFalse(MeetingDuration.isValidDuration("0.0166"));

        // EP2
        assertTrue(MeetingDuration.isValidDuration("0.0167"));
        assertTrue(MeetingDuration.isValidDuration("0.0168"));
        assertTrue(MeetingDuration.isValidDuration("0.02"));
        assertTrue(MeetingDuration.isValidDuration("2.0000"));
        assertTrue(MeetingDuration.isValidDuration("00000002.0000"));
        assertTrue(MeetingDuration.isValidDuration("23.9999"));
        assertTrue(MeetingDuration.isValidDuration("24"));
        assertTrue(MeetingDuration.isValidDuration("24.0000"));

        // EP3
        assertFalse(MeetingDuration.isValidDuration("24.0001"));
        assertFalse(MeetingDuration.isValidDuration("24.0002"));
        assertFalse(MeetingDuration.isValidDuration("1234.1"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MIN_VALUE)));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Double.POSITIVE_INFINITY)));
    }



}
