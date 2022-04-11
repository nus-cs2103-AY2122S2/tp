package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    private UserPrefs userPrefs1;
    private UserPrefs userPrefs2;
    private UserPrefs userPrefs1Copy;

    @BeforeEach
    public void init() {
        userPrefs1 = new UserPrefs();
        userPrefs1.setRecipeBookFilePath(Path.of("path/to/recipe/book"));
        userPrefs1.setGuiSettings(new GuiSettings(1, 2, 3, 4));

        userPrefs2 = new UserPrefs();
        userPrefs1Copy = new UserPrefs(userPrefs1);
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setRecipeBookFilePath(null));
    }

    @Test
    public void equals() {
        assertNotEquals(userPrefs1, userPrefs1.getGuiSettings());
        assertEquals(userPrefs1, userPrefs1);
        assertEquals(userPrefs1, userPrefs1Copy);
        assertNotEquals(userPrefs1, userPrefs2);
    }

    @Test
    public void hashCode_test() {
        assertEquals(userPrefs1.hashCode(), userPrefs1Copy.hashCode());
        assertNotEquals(userPrefs1.hashCode(), userPrefs2.hashCode());
    }

}
