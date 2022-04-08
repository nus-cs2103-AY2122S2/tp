package seedu.trackermon.commons.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests for {@code JarToolsTest}.
 */
public class JarToolsTest {

    /**
     * Tests getting classLocationString of the {@code JarToolstest} class.
     */
    @Test
    void getClassLocationStringMethod() {
        assertNotNull(JarTools.getClassLocationString(JarToolsTest.class));
    }

    /**
     * Checks that test should not be running in the jar file.
     */
    @Test
    void runningFromJarFileMethod() {
        assertFalse(JarTools.runningFromJarFile());
    }
}
