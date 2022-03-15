package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LogTest {

    @Test
    public void isValidLogTitle() {
        // null
        assertThrows(NullPointerException.class, () -> LogName.isValidName(null));

        // blank title
        assertFalse(LogName.isValidName("")); // empty string
        assertFalse(LogName.isValidName("              ")); // spaces only

        // valid length?
        String longTitle = "";
        for (int i = 0; i < LogName.TITLE_LENGTH_CONSTRAINT; i++) {
            longTitle += "c";
        }
        assertTrue(LogName.isValidName(longTitle)); // exactly as many characters
        assertFalse(LogName.isValidName(longTitle + "c")); // >limit characters
        assertFalse(LogName.isValidName(" peter jack")); // start with space

        // valid
        assertTrue(LogName.isValidName("12345")); // numbers only
        assertTrue(LogName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(LogName.isValidName("Capital Tan")); // with capital letters
        assertTrue(LogName.isValidName("David Roger Jackson Ray Jr 2nd")); // long name
        assertTrue(LogName.isValidName("`~!@#$%^&*()_-+={[}]|\\:;\"\'<,>.?/")); // random characters
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Log(null, null));
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
        assertEquals(testLog.getDescription(), description);
        assertEquals(testLog.getTitle(), title);
    }
}
