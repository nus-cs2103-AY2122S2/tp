package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiSettings;

public class UserPrefsTest {

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
    public void equals() {
        UserPrefs userPref1 = new UserPrefs();

        UserPrefs userPref2 = new UserPrefs();
        userPref2.setGuiSettings(new GuiSettings(1, 1, 1, 1));
        UserPrefs userPref3 = new UserPrefs();
        userPref3.setAddressBookFilePath(Path.of("/test/path"));
        UserPrefs userPref4 = new UserPrefs();
        userPref4.setScheduleFilePath(Path.of("/test/path2"));

        assertTrue(userPref1.equals(userPref1));
        assertTrue(userPref1.equals(new UserPrefs()));

        assertFalse(userPref1.equals(null));
        assertFalse(userPref1.equals(1));
        assertFalse(userPref1.equals(userPref2));
        assertFalse(userPref1.equals(userPref3));
        assertFalse(userPref1.equals(userPref4));
    }

    @Test
    public void hashcode() {
        UserPrefs userPref1 = new UserPrefs();

        UserPrefs userPref2 = new UserPrefs();
        userPref2.setGuiSettings(new GuiSettings(1, 1, 1, 1));
        UserPrefs userPref3 = new UserPrefs();
        userPref3.setAddressBookFilePath(Path.of("/test/path"));
        UserPrefs userPref4 = new UserPrefs();
        userPref4.setScheduleFilePath(Path.of("/test/path2"));

        assertTrue(userPref1.hashCode() == new UserPrefs().hashCode());

        assertFalse(userPref1.hashCode() == userPref2.hashCode());
        assertFalse(userPref1.hashCode() == userPref3.hashCode());
        assertFalse(userPref1.hashCode() == userPref4.hashCode());
    }

}
