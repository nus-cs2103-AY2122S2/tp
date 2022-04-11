package seedu.address.model.telegram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> new Telegram(Optional.empty()).value.get());
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(Optional.of(invalidHandle)));
    }

    @Test
    public void isValidHandle() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidHandle(null));

        // invalid addresses
        assertFalse(Telegram.isValidHandle("")); // empty string
        assertFalse(Telegram.isValidHandle(" ")); // spaces only
        assertFalse(Telegram.isValidHandle("@four")); // < 5
        assertFalse(Telegram.isValidHandle("@totalnumberofcharactersshereare32")); // > 32
        assertFalse(Telegram.isValidHandle("@endswith_")); // > 32
        assertFalse(Telegram.isValidHandle("@special*")); // > 32

        // valid addresses
        assertTrue(Telegram.isValidHandle("@validNoDigits"));
        assertTrue(Telegram.isValidHandle("@1919191"));
        assertTrue(Telegram.isValidHandle("@five1"));
        assertTrue(Telegram.isValidHandle("@totalnumberofcharactershereare32"));
        assertTrue(Telegram.isValidHandle("@hello_hehe")); // one character
    }
}
