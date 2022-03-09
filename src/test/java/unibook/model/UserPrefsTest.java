package unibook.model;

import org.junit.jupiter.api.Test;

import unibook.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setUniBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPrefs.setUniBookFilePath(null));
    }

}
