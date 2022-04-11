package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialName(null));
    }

    @Test
    public void constructor_invalidTutorialName_throwsIllegalArgumentException() {
        String invalidTutorialName = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialName(invalidTutorialName));
    }

    @Test
    public void isValidDay() {
        // null tutorial names
        assertThrows(NullPointerException.class, () -> TutorialName.isValidTutorialName(null));

        // invalid tutorial names
        assertFalse(TutorialName.isValidTutorialName("")); // empty string
        assertFalse(TutorialName.isValidTutorialName(" ")); // spaces only
        assertFalse(TutorialName.isValidTutorialName("T*")); // non-alphanumeric character

        // valid tutorial names
        assertTrue(TutorialName.isValidTutorialName("T04")); // Upper Case Letters with Numbers Only
        assertTrue(TutorialName.isValidTutorialName("LAB 3")); // Upper Case Letters with Numbers and Space
        assertTrue(TutorialName.isValidTutorialName("tut 3")); // Lower Case Letters with Numbers and Space
    }
}
