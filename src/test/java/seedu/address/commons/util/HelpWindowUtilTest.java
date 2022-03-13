package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;

class HelpWindowUtilTest {

    private static String validLink = "https://github.com/AY2122S2-CS2103-F11-2";
    private static String invalidLink = "invalid link";

    @Test
    public void userGuide_checkLink_throwsIoException() throws IOException {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(validLink);
        assertEquals(helpWindowUtil.convertToUrl(), URI.create("https://github.com/AY2122S2-CS2103-F11-2"));
    }

    @Test
    public void userGuide_checkInvalidLink_throwsIoException() throws IOException {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(invalidLink);
        assertThrows(IllegalArgumentException.class, () -> helpWindowUtil.convertToUrl());
    }

    @Test
    public void assertSameUrl() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(validLink);
        Object expectedModel = new HelpWindowUtil("https://github.com/AY2122S2-CS2103-F11-2");
        assertEquals(helpWindowUtil.getUrl(), ((HelpWindowUtil) expectedModel).getUrl());
    }

    @Test
    public void constructorTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(validLink);
        assertEquals(helpWindowUtil.getUrl(), validLink);
    }

    @Test
    public void desktopCheckTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(validLink);
        assertEquals(helpWindowUtil.isDesktopUsable(), Desktop.isDesktopSupported());
    }

    @Test
    public void getDesktopTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(validLink);
        if (Desktop.isDesktopSupported()) {
            assertEquals(helpWindowUtil.getDesktop(), Desktop.getDesktop());
        } else {
            assertEquals(helpWindowUtil.getDesktop(), null);
        }
    }
}
