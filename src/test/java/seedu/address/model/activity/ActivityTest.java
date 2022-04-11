package seedu.address.model.activity;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ActivityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Activity(null));
    }

    @Test
    public void constructor_invalidActivityName_throwsIllegalArgumentException() {
        String invalidActivityName = "";
        assertThrows(IllegalArgumentException.class, () -> new Activity(invalidActivityName));
    }

    @Test
    public void isValidActivityName() {
        // null activity name
        assertThrows(NullPointerException.class, () -> Activity.isValidActivityName(null));
    }

}
