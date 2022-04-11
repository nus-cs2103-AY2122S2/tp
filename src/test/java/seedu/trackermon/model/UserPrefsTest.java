package seedu.trackermon.model;

import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) for {@code UserPrefs}.
 */
public class UserPrefsTest {

    /**
     * Tests exception thrown when setting a null {@code GuiSettings}.
     */
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    /**
     * Tests exception thrown when setting a null showListFilePath.
     */
    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setShowListFilePath(null));
    }

}
