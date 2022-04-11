package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegrams
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("tele")); // less than 5 characters
        assertFalse(Telegram.isValidTelegram("@amy bee10")); // spaces within telegram

        // valid telegrams
        assertTrue(Telegram.isValidTelegram("@amybe")); // exactly 5 characters
        assertTrue(Telegram.isValidTelegram("johnsmith14")); // without '@' symbol
        assertTrue(Telegram.isValidTelegram("@NathanBalakrishnanTheMan1")); // long telegram
    }
}
