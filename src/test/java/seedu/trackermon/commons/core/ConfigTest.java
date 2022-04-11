package seedu.trackermon.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests for {@code Config}.
 */
public class ConfigTest {

    /**
     * Tests the string of a default {@code Config} object.
     */
    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    /**
     * Tests the equals method of {@code Config}.
     */
    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }

    /**
     * Tests the hash method of {@code Config}.
     */
    @Test
    public void hashMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.hashCode() == Objects.hash(defaultConfig.getLogLevel(),
                defaultConfig.getUserPrefsFilePath()));
    }

    /**
     * Tests getting userPrefsFilePath of a default {@code Config} object.
     */
    @Test
    public void getUserPrefsFilePath_fromEditor_success() {
        Config defaultConfig = new Config();
        assertEquals(Paths.get("preferences.json"), defaultConfig.getUserPrefsFilePath());
    }

}
