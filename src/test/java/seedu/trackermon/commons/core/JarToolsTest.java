package seedu.trackermon.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class JarToolsTest {

    @Test
    void getClassLocationStringMethod() {
        assertEquals("C:\\Users\\User\\Desktop"
                        + "\\NUS\\CS2103\\tp\\build\\classes\\java\\test\\seedu\\trackermon\\commons\\core",
                JarTools.getClassLocationString(JarToolsTest.class));
    }

    @Test
    void runningFromJarFileMethod() {
        assertFalse(JarTools.runningFromJarFile());
    }
}
