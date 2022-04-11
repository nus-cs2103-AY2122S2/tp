package seedu.address.model.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDesc_throwsIllegalArgumentException() {
        String invalidDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDesc));
    }

    @Test
    public void isValidDesc() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDesc(null));

        // invalid description
        assertFalse(Description.isValidDesc("")); // empty string
        assertFalse(Description.isValidDesc(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDesc("^")); // only non-alphanumeric characters
        assertTrue(Description.isValidDesc("homework*")); // contains non-alphanumeric characters
        assertTrue(Description.isValidDesc("do homework")); // alphabets only
        assertTrue(Description.isValidDesc("12345")); // numbers only
        assertTrue(Description.isValidDesc("do homework the 2nd")); // alphanumeric characters
        assertTrue(Description.isValidDesc("Do HoMeWorK")); // with capital letters
        assertTrue(Description.isValidDesc("From the Ghastly Eyrie I can see to the ends of the world and from this "
                + "vantage point I declare with utter certainty that this one is in the bag")); // long names
    }
}
