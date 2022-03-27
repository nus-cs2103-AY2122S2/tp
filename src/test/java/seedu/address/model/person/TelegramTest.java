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
    public void constructor_invalidTelegramId_throwsIllegalArgumentException() {
        String invalidTelegramId = "alice@@";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegramId));
    }

    @Test
    public void isValidTelegramId() {
        // null telegram id
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegramId(null));

        // invalid telegram id
        assertFalse(Telegram.isValidTelegramId("@")); // symbols
        assertFalse(Telegram.isValidTelegramId("alice test")); // two words


        // valid telegram id
        assertTrue(Telegram.isValidTelegramId("")); // blank spaces (after trimmed)
        assertTrue(Telegram.isValidTelegramId("alice123"));
        assertTrue(Telegram.isValidTelegramId("alice_123")); // with underscore
        assertTrue(Telegram.isValidTelegramId("alice_test_1234")); // long telegram id
    }
}
