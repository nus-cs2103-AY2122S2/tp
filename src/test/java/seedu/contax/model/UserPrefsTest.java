package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiSettings;

public class UserPrefsTest {

    private static final GuiSettings SAMPLE_GUI_SETTINGS = new GuiSettings(1, 1, 1, 1);
    private static final Path SAMPLE_PATH1 = Path.of("/test/path1");
    private static final Path SAMPLE_PATH2 = Path.of("/test/path2");

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void setScheduleFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setScheduleFilePath(null));
    }

    @Test
    public void resetData() {
        UserPrefs refUserPref = new UserPrefs();
        refUserPref.setGuiSettings(SAMPLE_GUI_SETTINGS);
        refUserPref.setAddressBookFilePath(SAMPLE_PATH1);
        refUserPref.setScheduleFilePath(SAMPLE_PATH2);

        UserPrefs newUserPref = new UserPrefs(refUserPref);
        assertEquals(SAMPLE_GUI_SETTINGS, newUserPref.getGuiSettings());
        assertEquals(SAMPLE_PATH1, newUserPref.getAddressBookFilePath());
        assertEquals(SAMPLE_PATH2, newUserPref.getScheduleFilePath());
    }

    @Test
    public void toStringTest() {
        UserPrefs refUserPref = new UserPrefs();
        refUserPref.setGuiSettings(SAMPLE_GUI_SETTINGS);
        refUserPref.setAddressBookFilePath(SAMPLE_PATH1);
        refUserPref.setScheduleFilePath(SAMPLE_PATH2);

        String stringConversion = refUserPref.toString();
        assertTrue(stringConversion.contains("Gui Settings : " + SAMPLE_GUI_SETTINGS));
        assertTrue(stringConversion.contains("Address Book data file location : " + SAMPLE_PATH1));
        assertTrue(stringConversion.contains("Schedule data file location: " + SAMPLE_PATH2));
    }

    @Test
    public void equals() {
        UserPrefs refUserPref = new UserPrefs();

        UserPrefs differentGuiSettings = new UserPrefs();
        differentGuiSettings.setGuiSettings(SAMPLE_GUI_SETTINGS);

        UserPrefs differentAddressBookPath = new UserPrefs();
        differentAddressBookPath.setAddressBookFilePath(SAMPLE_PATH1);

        UserPrefs differentSchedulePath = new UserPrefs();
        differentSchedulePath.setScheduleFilePath(SAMPLE_PATH2);

        assertTrue(refUserPref.equals(refUserPref)); // Same object
        assertTrue(refUserPref.equals(new UserPrefs())); // Same data

        assertFalse(refUserPref.equals(null)); // Null
        assertFalse(refUserPref.equals(1)); // Different type

        // Different data
        assertFalse(refUserPref.equals(differentGuiSettings));
        assertFalse(refUserPref.equals(differentAddressBookPath));
        assertFalse(refUserPref.equals(differentSchedulePath));
    }

    @Test
    public void hashcode() {
        UserPrefs refUserPref = new UserPrefs();

        UserPrefs differentGuiSettings = new UserPrefs();
        differentGuiSettings.setGuiSettings(SAMPLE_GUI_SETTINGS);

        UserPrefs differentAddressBookPath = new UserPrefs();
        differentAddressBookPath.setAddressBookFilePath(SAMPLE_PATH1);

        UserPrefs differentSchedulePath = new UserPrefs();
        differentSchedulePath.setScheduleFilePath(SAMPLE_PATH2);

        assertTrue(refUserPref.hashCode() == new UserPrefs().hashCode());

        assertFalse(refUserPref.hashCode() == differentGuiSettings.hashCode());
        assertFalse(refUserPref.hashCode() == differentGuiSettings.hashCode());
        assertFalse(refUserPref.hashCode() == differentGuiSettings.hashCode());
    }

}
