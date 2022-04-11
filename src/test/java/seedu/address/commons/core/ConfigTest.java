package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();

        assertNotNull(defaultConfig);
        assertNotEquals(defaultConfig, defaultConfig.getLogLevel());
        assertEquals(defaultConfig, defaultConfig);
        assertEquals(defaultConfig, defaultConfig2);

        assertEquals(defaultConfig.getUserPrefsFilePath(), defaultConfig2.getUserPrefsFilePath());
        assertEquals(defaultConfig.hashCode(), defaultConfig2.hashCode());
    }


}
