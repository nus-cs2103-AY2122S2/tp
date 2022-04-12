package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Requirement(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = " ";
        String invalidDescription2 = "";
        String invalidDescription3 = " Hello";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription2));
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription3));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescriptionText(null));

        // invalid descriptions
        assertFalse(Description.isValidDescriptionText("")); // empty string
        assertFalse(Description.isValidDescriptionText(" ")); // spaces only
        assertFalse(Description.isValidDescriptionText("   great job")); // string starting with spaces

        // valid descriptions
        assertTrue(Description.isValidDescriptionText("Job is a remote; May require duty on the weekends"));
        assertTrue(Description.isValidDescriptionText("-"));
        assertTrue(Description.isValidDescriptionText("Part-timers only. \nMust have flexible work time"));
    }
}
