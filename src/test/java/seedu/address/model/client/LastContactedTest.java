package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LastContactedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastContacted(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new LastContacted(invalidDateTime));
    }

    @Test
    public void isValidLastContacted() {
        // null date times
        assertThrows(NullPointerException.class, () -> LastContacted.isValidLastContacted(null));
        assertThrows(NullPointerException.class, () -> LastContacted.isPastLastContacted((null)));

        // invalid date times
        assertFalse(LastContacted.isValidLastContacted("")); // empty string
        assertFalse(LastContacted.isValidLastContacted(" ")); // spaces only
        assertFalse(LastContacted.isValidLastContacted("00-00-0000 00:00")); // all zeros
        assertFalse(LastContacted.isValidLastContacted("21-03-2022")); // date only
        assertFalse(LastContacted.isValidLastContacted("21:03")); // time only
        assertFalse(LastContacted.isPastLastContacted("21-03-2032 21:03")); // future date time

        // valid date time
        assertTrue(LastContacted.isValidLastContacted("21-03-2022 21:03"));
    }
}
