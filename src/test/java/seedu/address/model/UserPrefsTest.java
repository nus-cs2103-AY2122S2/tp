package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setContactListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setContactListFilePath(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        UserPrefs userPref = new UserPrefs();
        assertTrue(userPref.equals(userPref));

        // same object -> returns true
        assertTrue(userPref.equals(userPref));

        // null -> returns false
        assertFalse(userPref.equals(null));

        // different type -> returns false
        assertFalse(userPref.equals(5));

        // different userPrefs -> returns false
        assertFalse(userPref.equals(ASSIGNMENT));

        // different GUISettings -> returns false
        UserPrefs editedPrefs = new UserPrefs();
        GuiSettings guiSettings = new GuiSettings(0, 0, 0, 0);
        editedPrefs.setGuiSettings(guiSettings);
        assertFalse(userPref.equals(editedPrefs));

        // different contactListFilePath -> returns false
        editedPrefs = new UserPrefs();
        editedPrefs.setContactListFilePath(Paths.get("BAD"));
        assertFalse(userPref.equals(editedPrefs));


        // different taskListFilePath -> returns false
        editedPrefs = new UserPrefs();
        editedPrefs.setTaskListFilePath(Paths.get("BAD"));
        assertFalse(userPref.equals(editedPrefs));

    }

}
