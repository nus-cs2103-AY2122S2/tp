package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidHandle));
    }

    @Test
    public void isValidTelegram() {
        // null name
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid name
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("^")); // only non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("peter*")); // contains non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("pete")); // not 5 characters long

        // valid name
        assertTrue(Telegram.isValidTelegram("12345")); // numbers only
        assertTrue(Telegram.isValidTelegram("peterjack")); // alphabets only
        assertTrue(Telegram.isValidTelegram("peterthe2nd")); // alphanumeric characters
        assertTrue(Telegram.isValidTelegram("CapitalTan")); // with capital letters
        assertTrue(Telegram.isValidTelegram("CapitalTan_")); // with underscores
        assertTrue(Telegram.isValidTelegram("DavidRogerJacksonRayJr2nd")); // long names
    }
}
