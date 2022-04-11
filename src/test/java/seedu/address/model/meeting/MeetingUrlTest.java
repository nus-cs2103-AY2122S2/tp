package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.net.URL;

import org.junit.jupiter.api.Test;

public class MeetingUrlTest {
    public static final String INVALID_URL = "'invalid url'";

    public static final String VALID_URL = "https://www.google.com";

    public static final String VALID_URL_NO_HTTPS = "www.google.com";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingUrl(null));
    }

    @Test
    public void constructor_invalidUrl_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, MeetingUrl.MESSAGE_CONSTRAINTS, () ->
                new MeetingUrl(INVALID_URL));
    }

    @Test
    public void isValidUrl_invalidUrl_returnsFalse() {
        assertFalse(MeetingUrl.isValidUrl(INVALID_URL));
    }

    @Test
    public void isValidUrl_validUrl_returnsTrue() {
        assertTrue(MeetingUrl.isValidUrl(VALID_URL));
        assertTrue(MeetingUrl.isValidUrl(VALID_URL_NO_HTTPS));
    }

    /*
     * The parseUrl method always returns a URL, as the string to be parsed will always be
     * prepended with "https://"
     */
    @Test
    public void parseUrl_validUrl_returnsUrl() throws Exception {
        assertEquals(MeetingUrl.parseUrl(VALID_URL), new URL(VALID_URL));
        assertEquals(MeetingUrl.parseUrl(VALID_URL_NO_HTTPS), new URL(VALID_URL));
    }

    @Test
    public void checkAndPrependHttps_urlWithoutHttps_prependsHttps() {
        assertEquals(VALID_URL, MeetingUrl.checkAndPrependHttps(VALID_URL_NO_HTTPS));
        final String string = "Test String";
        final String expectedString = "https://" + string;
        assertEquals(expectedString, MeetingUrl.checkAndPrependHttps(string));
    }

    @Test
    public void equals() {
        final MeetingUrl meetingUrl = new MeetingUrl(VALID_URL);
        // null -> false
        assertFalse(meetingUrl.equals(null));

        // different type -> false
        assertFalse(meetingUrl.equals(1));

        // different URL -> false
        final MeetingUrl meetingUrlDifferentUrl = new MeetingUrl("www.facebook.com");
        assertFalse(meetingUrl.equals(meetingUrlDifferentUrl));

        // same URL -> true
        final MeetingUrl meetingUrlClone = new MeetingUrl(VALID_URL);
        assertTrue(meetingUrl.equals(meetingUrlClone));

        // same instance -> true
        assertTrue(meetingUrl.equals(meetingUrl));
    }
}
