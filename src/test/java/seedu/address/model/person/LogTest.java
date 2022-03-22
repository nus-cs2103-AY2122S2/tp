package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.common.Description;

public class LogTest {

    @Test
    public void isValidLogTitle() {
        // null
        assertThrows(NullPointerException.class, () -> LogName.isValidLogName(null));

        // blank title
        assertFalse(LogName.isValidLogName("")); // empty string
        assertFalse(LogName.isValidLogName("              ")); // spaces only

        // valid length?
        String longTitle = "";
        for (int i = 0; i < LogName.TITLE_LENGTH_CONSTRAINT; i++) {
            longTitle += "c";
        }
        assertTrue(LogName.isValidLogName(longTitle)); // exactly as many characters
        assertFalse(LogName.isValidLogName(longTitle + "c")); // >limit characters
        assertFalse(LogName.isValidLogName(" peter jack")); // start with space

        // valid
        assertTrue(LogName.isValidLogName("12345")); // numbers only
        assertTrue(LogName.isValidLogName("peter the 2nd")); // alphanumeric characters
        assertTrue(LogName.isValidLogName("Capital Tan")); // with capital letters
        assertTrue(LogName.isValidLogName("David Roger Jackson Ray Jr 2nd")); // long name
        assertTrue(LogName.isValidLogName("`~!@#$%^&*()_-+={[}]|\\:;\"\'<,>.?/")); // random characters
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Log(null, "some description"));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> new Log("", null)); // empty
        assertThrows(IllegalArgumentException.class, () -> new Log("      ", null)); // spaces only

        String longTitle = "";
        for (int i = 0; i < LogName.TITLE_LENGTH_CONSTRAINT + 1; i++) {
            longTitle += "c";
        }
        final String tooLongTitle = longTitle;
        assertThrows(IllegalArgumentException.class, () -> new Log(tooLongTitle, null)); // too many characters

    }

    @Test
    public void constructor_noDescription_defaultDescription() {
        String title = "title";
        assertEquals(new Log(title, Log.DEFAULT_NO_DESCRIPTION), new Log(title, null));
    }

    @Test
    public void constructor_haveDescription_givenDescription() {
        String title = "title";
        String description = "description";
        Log testLog = new Log(title, description);
        assertEquals(testLog.getTitle(), new LogName(title));
        assertEquals(testLog.getDescription(), new Description(description));
    }
}
