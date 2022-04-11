package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GuiSettingsTest {

    @Test
    void equals_test() {
        final GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        final GuiSettings guiSettings2 = new GuiSettings(2, 3, 4, 5);
        final GuiSettings guiSettingsCopy = new GuiSettings(guiSettings.getWindowWidth(),
                guiSettings.getWindowHeight(),
                guiSettings.getWindowCoordinates().x,
                guiSettings.getWindowCoordinates().y);

        assertNotEquals(guiSettings, guiSettings.getWindowCoordinates());
        assertEquals(guiSettings, guiSettings);
        assertEquals(guiSettings, guiSettingsCopy);
        assertNotEquals(guiSettings, guiSettings2);

        assertEquals(guiSettings.hashCode(), guiSettingsCopy.hashCode());
        assertNotEquals(guiSettings.hashCode(), guiSettings2.hashCode());
    }
}
