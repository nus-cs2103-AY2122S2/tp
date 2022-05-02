package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;

class HelpWindowUtilTest {

    private static final String VALID_LINK = "https://github.com/AY2122S2-CS2103-F11-2";
    private static final String INVALID_LINK = "invalid link";

    @Test
    public void userGuide_checkLink_throwsIoException() throws IOException {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        assertEquals(helpWindowUtil.convertToUrl(), URI.create("https://github.com/AY2122S2-CS2103-F11-2"));
    }

    @Test
    public void userGuide_checkInvalidLink_throwsIoException() throws IOException {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(INVALID_LINK);
        assertThrows(IllegalArgumentException.class, () -> helpWindowUtil.convertToUrl());
    }

    @Test
    public void assertSameUrl() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        Object expectedModel = new HelpWindowUtil("https://github.com/AY2122S2-CS2103-F11-2");
        assertEquals(helpWindowUtil.getUrl(), ((HelpWindowUtil) expectedModel).getUrl());
    }

    @Test
    public void helpWindowUtil_constructorTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        assertEquals(helpWindowUtil.getUrl(), VALID_LINK);
    }

    @Test
    public void helpWindowUtil_desktopCheckTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        assertEquals(helpWindowUtil.isDesktopUsable(), Desktop.isDesktopSupported());
    }

    @Test
    public void helpWindowUtil_getDesktopTest() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        if (Desktop.isDesktopSupported()) {
            assertEquals(helpWindowUtil.getDesktop(), Desktop.getDesktop());
        } else {
            assertThrows(HeadlessException.class, () -> helpWindowUtil.getDesktop());
        }
    }

    @Test
    public void helpWindowUtil_getDesktopExceptionTest_throwsHeadlessException() {
        HelpWindowUtil helpWindowUtil = new HelpWindowUtil(VALID_LINK);
        if (!Desktop.isDesktopSupported()) {
            assertThrows(HeadlessException.class, () -> helpWindowUtil.getDesktop());
        }
    }
}
