package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.assertAllFalse;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // test all common string restrictions
        assertAllFalse(Name::isValidName);

        // invalid name
        assertFalse(Name.isValidName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor em")); // 256 characters

        // valid name
        assertTrue(Description.isValidDescription("H")); // one character
        assertTrue(Name.isValidName("complete tutorials")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("complete cs3240 milestones")); // alphanumeric characters
        assertTrue(Name.isValidName("Complete Tutorials")); // with capital letters
        assertTrue(Description.isValidDescription("T[G2] Complete Milestone")); // Punctation not at first character
        // contains non-alphanumeric characters
        assertTrue(Name.isValidName("Complete CS3240 Milestones, Tutorials and Lectures"));
        assertTrue(Name.isValidName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor e")); // 255 characters
    }
}